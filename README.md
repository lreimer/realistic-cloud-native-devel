# Realistic Development of Cloud-native Applications

## Using Docker BuildKit

```bash
$ DOCKER_BUILDKIT=1 docker build -t realistic-cloud-native-devel .
```

```json
{"debug":true,"experimental":true,"features":{"buildkit":true}}
```


## Using Docker Compose

```bash
$ docker-compose build
$ docker-compose up --build -d
$ docker-compose logs -f

$ docker-compose stop
$ docker-compose start
$ docker-compose down
```

## Using TestContainers

```groovy
dependencies {
    testCompile "org.testcontainers:spock:1.12.5"
    testCompile "org.testcontainers:nginx:1.12.5"

    testCompile "org.testcontainers:postgresql:1.12.5"
    testCompile 'org.postgresql:postgresql:42.2.10'
}
```

## Docker Stack and Kubernetes

https://github.com/docker/compose-on-kubernetes/blob/master/README.md

```bash
$ docker stack deploy --orchestrator=kubernetes -c docker-compose.yml realistic-cloud-native-devel
$ docker stack ls
$ docker stack services realistic-cloud-native-devel
$ docker stack rm realistic-cloud-native-devel
```

## Using Kompose

https://kubernetes.io/docs/tasks/configure-pod-container/translate-compose-kubernetes/#use-kompose

```bash
$ brew install kompose

$ kompose up --help
$ kompose up --build=local --push-image=false
$ kompose up --build=none
$ kompose up --controller=deployment --verbose --error-on-warning --volumes=emptyDir
$ kompose down

$ kompose convert --controller=deployment --out kubernetes
$ kompose convert --chart --out charts
```

## Using Hoverfly

https://docs.hoverfly.io/en/latest/pages/introduction/downloadinstallation.html

```bash
$ brew install SpectoLabs/tap/hoverfly

$ hoverctl version
$ hoverctl targets

$ hoverctl start
$ open http://localhost:8888

$ hoverctl mode capture

$ export HTTP_PROXY=http://localhost:8500
$ export HTTPS_PROXY=http://localhost:8500

$ http --verify=no get https://samples.openweathermap.org/data/2.5/weather q==London,uk appid==b6907d289e10d714a6e88b30761fae22

$ hoverctl mode simulate
$ http --verify=no get https://samples.openweathermap.org/data/2.5/weather q==London,uk appid==b6907d289e10d714a6e88b30761fae22

$ http --verify=no get https://samples.openweathermap.org/data/2.5/weather q==Rosenheim,de appid==b6907d289e10d714a6e88b30761fae22
$ hoverctl mode spy

$ hoverctl stop
$ docker-compose up
$ hoverctl mode simulate -t docker
```

## Using kubefwd

https://github.com/txn2/kubefwd

```bash
$ brew install kubefwd
$ scoop install kubefwd

$ kubefwd svc -n default
$ http get microservice:8080/api/weather

$ kubefwd svc -n default -l tier=backend -d demo
$ http get microservice.demo:8080/api/weather
```

## Further References

- https://github.com/moby/buildkit
- https://github.com/ksync/ksync

## Maintainer

M.-Leander Reimer (@lreimer), <mario-leander.reimer@qaware.de>

## License

This software is provided under the MIT open source license, read the `LICENSE`
file for details.
