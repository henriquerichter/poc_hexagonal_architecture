FROM public.ecr.aws/docker/library/amazoncorretto:21.0.2-alpine3.19 as build
ENV HOME=/usr/app

RUN mkdir -p $HOME
WORKDIR $HOME
ADD . $HOME
RUN --mount=type=cache,target=/root/.m2 ./mvnw -f $HOME/pom.xml clean package

FROM public.ecr.aws/docker/library/amazoncorretto:21.0.2-alpine3.19
ARG JAR_FILE=/usr/app/infrastructure/target/*.jar
COPY --from=build $JAR_FILE /app.jar
ENV JAVA_TOOL_OPTIONS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=80.0 -XX:InitialRAMPercentage=80.0"
ENTRYPOINT ["java","-XshowSettings:vm","-jar","/app.jar"]
