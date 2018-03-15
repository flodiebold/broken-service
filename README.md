A sample service to be subject of heap dumps

Run with:

./gradlew build jar  && docker build . -t broken1:latest  && docker run broken1:latest


You can create misbehavior by calling the rest service:
 http://172.17.0.2:8080/breakme?type=1

The IP is convinently logged to console when you run the container.

Modes:

- 1: Leak something (go and find what is leaked)

