FROM openjdk:11-jdk-slim

RUN apt-get update && apt-get install -y \
    maven \
    android-tools-adb \
    curl \
    && curl -fsSL https://get.docker.com -o get-docker.sh \
    && sh get-docker.sh \
    && rm -rf /var/lib/apt/lists/* get-docker.sh

WORKDIR /workspace

COPY pom.xml .
RUN mvn dependency:resolve

COPY src ./src

CMD ["mvn", "clean", "test"]