all: docker
	mvn install

dev:
	mvn spring-boot:run -Dspring.profiles.active=dev

prod:
	mvn spring-boot:run -Dspring.profiles.active=prod

docker:
	docker-compose up -d

test:
	mvn -B verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dspring.profiles.active=ci -Dsonar.projectKey=Steen3S_vkbep-18

clean:
	rm -r target/