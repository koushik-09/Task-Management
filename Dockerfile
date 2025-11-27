FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-ea-28-jdk-slim
COPY --from=build /target/task-management-0.1.jar task_mgmt.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","task_mgmt.jar"]
