# Prometheus metrics not scraped

because of BCLOSE.

```
expected label name, got "BCLOSE"
```
The issue is linked here https://github.com/prometheus/prometheus/issues/6284

## Installation

You need to install docker and docker-compose.

Make sure to have docker on your path.

### Docker
```
docker -v
```
```
Docker version 17.12.0-ce, build c97c6d6
```


## How to run this?

```
docker run -it --rm --name my-maven-project -v "$PWD":/usr/src/app -w /usr/src/app maven:3.6.2-jdk-11-slim mvn clean install
docker-compose build
docker-compose up
```

## Verify everything works correctly

```
λ :~/development/git/broken-metrics (master)*$ docker images ourapp/metrics 
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
ourapp/metrics      0.0.1               54d3e3bc4dad        8 minutes ago       630MB
λ :~/development/git/broken-metrics (master)*$ curl localhost:8080/prometheus
# HELP hystrix_threadpool_threads_active_current_count The approximate number of threads that are actively executing tasks.
# TYPE hystrix_threadpool_threads_active_current_count gauge
hystrix_threadpool_threads_active_current_count{build_version="local_build"} 0.0
```

