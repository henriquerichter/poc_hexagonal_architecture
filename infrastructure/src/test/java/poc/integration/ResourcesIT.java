package poc.integration;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.localstack.LocalStackContainer.Service;
import org.testcontainers.utility.DockerImageName;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.s3.model.CreateBucketRequest;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class ResourcesIT {

    @LocalServerPort
    private Integer port;

    private static final MySQLContainer<?> mySQLContainer;
    private static final LocalStackContainer localStackContainer;

    static {
        mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.3.0"))
                .withDatabaseName("test_db")
                .withUsername("test")
                .withPassword("test")
                .withInitScript("schema.sql");

        mySQLContainer.start();

        localStackContainer = new LocalStackContainer(DockerImageName.parse("localstack/localstack:3.2.0"))
                .withServices(LocalStackContainer.Service.S3)
                .withEnv("DEFAULT_REGION", "us-east-1");

        localStackContainer.start();

        createS3Bucket();
    }

    @DynamicPropertySource
    private static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
        registry.add("aws.s3.endpoint", () -> localStackContainer.getEndpointOverride(Service.S3).toString());
        registry.add("aws.s3.region", localStackContainer::getRegion);
    }

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
    }

    private static void createS3Bucket() {
        S3ClientBuilder s3ClientBuilder = S3Client.builder()
                .credentialsProvider(
                        StaticCredentialsProvider.create(
                                AwsBasicCredentials.create(ResourcesIT.localStackContainer.getAccessKey(), ResourcesIT.localStackContainer.getSecretKey())))
                .endpointOverride(ResourcesIT.localStackContainer.getEndpointOverride(Service.S3))
                .region(Region.of(ResourcesIT.localStackContainer.getRegion()));

        try (S3Client s3Client = s3ClientBuilder.build()) {
            CreateBucketRequest createBucketRequest = CreateBucketRequest.builder().bucket("composter").build();
            s3Client.createBucket(createBucketRequest);
        }
    }
}
