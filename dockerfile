FROM maven:3.8.4-openjdk-17
WORKDIR /app
# Copy Maven Wrapper and configuration
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
# Copy source files
COPY src ./src
# Make the mvnw script executable
RUN chmod +x ./mvnw
# Use the Maven Wrapper for the package command
RUN ./mvnw clean package spring-boot:repackage -DskipTests
EXPOSE 8080
# Run the application
ENTRYPOINT ["java", "-jar", "target/library-management-system-0.0.1-SNAPSHOT.jar"]