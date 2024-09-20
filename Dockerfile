FROM alpine

WORKDIR /app

COPY . .

RUN apk add openjdk22-jdk --repository=http://dl-cdn.alpinelinux.org/alpine/edge/testing/

RUN apk add maven

RUN mvn clean install -U

CMD [ "mvn", "spring-boot:run" ]


