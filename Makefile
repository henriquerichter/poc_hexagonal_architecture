docker-up:
	docker-compose build; docker-compose up -d

docker-down:
	docker-compose down -v

docker-build:
	docker build -t poc:latest .

docker-run:
	docker run -p 8080:8080 poc:latest
