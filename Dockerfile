FROM openjdk:11-jdk-slim

RUN apt-get update && apt-get install -y \
    maven \
    android-tools-adb \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /workspace

COPY pom.xml .
RUN mvn dependency:resolve

COPY src ./src

CMD ["mvn", "clean", "test"]
