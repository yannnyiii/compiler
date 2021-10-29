FROM openjdk:8
COPY . /myapp/
WORKDIR /myapp/
RUN javac -cp src/ src/test.java -d dst/