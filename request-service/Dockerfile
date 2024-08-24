FROM openjdk:19-alpine

# Set the working directory
WORKDIR /app

# Copy the Common Directory
COPY ../common /app/common

# Copy the Request Service Directory
COPY . .

# Build the project
RUN ./gradlew build

# Expose the service on 8080
EXPOSE 8080

# Shift to the Request Service Directory
WORKDIR /app/request-service
# Run the Service
CMD ["./gradlew", "run"]
