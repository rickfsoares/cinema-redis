FROM alpine

COPY . .

RUN apk update

RUN apk upgrade

RUN apk add --no-cache bash 

RUN apk add openjdk22-jdk --repository=http://dl-cdn.alpinelinux.org/alpine/edge/testing/

RUN apk add maven

RUN mvn clean install -U

ENTRYPOINT [ "/bin/bash", "-c" ]

CMD [ "mvn", "spring-boot:run" ]


