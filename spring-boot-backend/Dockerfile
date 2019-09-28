FROM openjdk:8-jdk-slim
RUN mkdir /source_code
WORKDIR /source_code

# Copy minimal gradle files and cache docker in container
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle/ gradle/
RUN ./gradlew --version

COPY src src/

#Run unit tests (but NOT system integration tests) and build the app
RUN ./gradlew build --info

CMD ./gradlew bootRun