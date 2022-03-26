all: docker
	mvn install

dev:
	mvn spring-boot:run -D spring-boot.run.profiles=dev

prod:
	mvn spring-boot:run -D spring-boot.run.profiles=prod

docker:
	docker-compose up -d

test:
	mvn -B verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dspring-boot.run.profiles=ci -Dsonar.projectKey=Steen3S_vkbep-18

clean:
	rm -r target/