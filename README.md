Web crawler

Make sure you've:

Java 11, Gradle 5.6.4

To view swagger-ui docs:

http://localhost:8080/buildit/swagger-ui/

How to run :-

1) Clone this repository in local machine (git clone ..)
2) ./gradlew clean build --parallel
3) java -jar build/libs/web-crawler-service-0.0.1-SNAPSHOT.jar

To test :-

POST to: http://localhost:8080/buildit/web/crawl with body {"baseUrl":"http://wiprodigital.com"} and header Content-Type:application/json

Improvement Areas:-

1) We can add max depth to search for links & cover any missing edge cases
2) Retry logic if a link is not crawled successfully and ended up with error
3) Can be scaled by deploying through kubernetes pods and configure replica count
4) Add jacoco and sonar code coverage and build breaker plugin
5) Clean up gradle dependencies to use latest versions
6) Add cucumber tests to define scenarios and implement step definitions in BDD style
7) Add a repository to store links details for a base url in database
8) Add some monitoring(Ex: Cloud watch metrics) on any repeated/dirty requests for same domain again and again
9) Add security to validate user using JWTToken to ensure only validated requests can reach controller
10) Add spring security with JwtFilter
11) Add checkstyle/PMD checks
12) Add DockerFile to create docker image
13) Add build pipeline which can build docker image and push it to remote repository
14) Deploy the docker image to kubernetes using helm charts to run this service in a kube pod
15) Investigate if we can better than using jsoup
