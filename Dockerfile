FROM alpine:latest as build

RUN apk update
RUN apk add openjdk20

COPY . .

RUN chmod +x ./gradlew
RUN ./gradlew bootJar --no-daemon

FROM openjdk:20-alpine

EXPOSE 9000

COPY --from=build ./build/libs/*.jar ./app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]