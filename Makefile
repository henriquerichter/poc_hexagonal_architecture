docker-up:
	docker-compose build; docker-compose up -d

docker-down:
	docker-compose down

docker-build:
	docker build -t test:latest .

docker-run:
	docker run -p 8080:8080 test:latest
