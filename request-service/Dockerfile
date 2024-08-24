FROM openjdk:19-alpine

# Install bash and dos2unix for line ending conversions
RUN apk update && apk add bash dos2unix

# Set the working directory
WORKDIR /app

# Copy the Common Directory
COPY ../common /app/common

# Copy the Request Service Directory
COPY . .

# Shift to the Request Service Directory
WORKDIR /app/request-service

# DEBUG - List the contents of the directory
RUN ls -la

# Ensure the gradlew script has correct permissions
RUN chmod +x gradlew

# Fix line endings in gradlew script
RUN dos2unix gradlew

# Build the project using bash
RUN bash ./gradlew build

# Expose the service on 8080
EXPOSE 8080

# Run the Service
CMD ["bash","./gradlew", "run"]
