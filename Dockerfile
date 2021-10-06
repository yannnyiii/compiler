FROM openjdk:8
WORKDIR /app/
COPY src/*.java ./
RUN javac test.java