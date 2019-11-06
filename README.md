# Prometheus metrics not scraped

because of BCLOSE.

```
expected label name, got "BCLOSE"
```

## Installation

You need to install docker and docker-compose.
Additionally OpenJDK 11 and Maven 3.x.x

Make sure to have docker, java and maven on your path.

### Docker
```
docker -v
```
```
Docker version 17.12.0-ce, build c97c6d6
```
### Java 
```
java -version
```
```
openjdk version "11.0.2" 2019-01-15
OpenJDK Runtime Environment 18.9 (build 11.0.2+9)
OpenJDK 64-Bit Server VM 18.9 (build 11.0.2+9, mixed mode)
```
### Maven
```
mvn -version
```
```
OpenJDK 64-Bit Server VM warning: Ignoring option MaxPermSize; support was removed in 8.0
Apache Maven 3.5.2 (138edd61fd100ec658bfa2d307c43b76940a5d7d; 2017-10-18T09:58:13+02:00)
Maven home: /home/user/development/tools/apache-maven-3.5.2
Java version: 11.0.2, vendor: Oracle Corporation
Java home: /home/user/development/tools/jdk-11.0.2
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "4.15.0-66-generic", arch: "amd64", family: "unix"
```

## How to run this?

```
mvn clean install
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

