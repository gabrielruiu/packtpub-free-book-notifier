FROM maven:3.3.9-jdk-8
WORKDIR /app

RUN git clone https://github.com/gabrielruiu/packtpub-free-book-notifier
RUN mvn -f packtpub-free-book-notifier/pom.xml clean package

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","packtpub-free-book-notifier/target/packtpub-free-book-notifier-1.0-SNAPSHOT.jar"]