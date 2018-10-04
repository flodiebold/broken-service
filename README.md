A sample service to be subject of heap dumps

Run with:

```
./gradlew build jar && docker build . -t broken1:latest && docker run -p 8080:8080 -p 9010:9010 broken1:latest
```

You can create misbehavior by calling the rest service:
 http://127.0.0.1:8080/breakme?type=1

Modes:

- 1: Leak something (go and find what is leaked)
- 2: Leak something else

