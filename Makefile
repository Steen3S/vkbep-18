all: docker
	mvn install

run:
	mvn spring-boot:run

docker:
	docker-compose up -d

clean:
	rm -r target/