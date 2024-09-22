FROM gradle:8-jdk-21-and-22-jammy AS build

USER root

WORKDIR /app

# Install bash and dos2unix for line ending conversions
RUN \
    apt-get update \
    && apt-get install -y bash dos2unix \
    && apt-get install --yes --no-install-recommends git \
    && rm --recursive --force /var/lib/apt/lists/*

# run remainder of tasks as the unprivileged 'gradle' user
#USER gradle

# Copy Required Directories
COPY ./common /app/common
COPY ./build.gradle ./
COPY  ./settings.gradle ./
COPY ./request-service /app/request-service

# Shift to the Request Service Directory
WORKDIR /app/request-service

# add a volume for Gradle home
VOLUME "/home/gradle/.gradle"

# Build the project using bash
#RUN bash ./gradlew -Dorg.gradle.welcome=never --no-daemon build

# Build the project using gradle command
RUN gradle -Dorg.gradle.welcome=never --no-daemon installDist


### RUN THE PROJECT ###
FROM eclipse-temurin:21-jre-jammy

# copy the Gradle build output from the build stage
COPY --from=build /app/request-service/build/install/request-service /home/deployment
COPY --from=build /app/request-service/static /home/deployment/static

RUN ls -l /home/deployment/static

# switch into the stand-alone service folder
WORKDIR /home/deployment

# limit the Java heap size
ENV \
	_JAVA_OPTIONS=-Xmx256M

# Run the Service
#CMD ["./gradlew", "-Dorg.gradle.welcome=never", "--no-daemon", "run"]
CMD ["bin/request-service"]

