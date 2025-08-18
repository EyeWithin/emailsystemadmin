FROM eclipse-temurin:17-jdk-alpine

# Install Maven
RUN apk add --no-cache maven

WORKDIR /app

# Copy the project files
COPY pom.xml .
COPY src ./src
COPY system.properties .

# Build the application
RUN mvn clean package -DskipTests

# Run the application
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/target/emailsystemadmin-0.0.1-SNAPSHOT.jar"]