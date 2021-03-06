# Realistic Development of Cloud-native Applications

## Using Docker BuildKit

- https://github.com/moby/buildkit

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

## Using Telepresence

https://www.telepresence.io/tutorials/docker

```bash
$ brew cask install osxfuse
$ brew install datawire/blackbird/telepresence

$ brew unlink python@2
$ brew link python@3

$ kubectl run hello-world --image=datawire/hello-world --port 8000 --expose
$ telepresence --docker-run --rm -it pstauffer/curl curl http://hello-world:8000/

$ kubectl port-forward deployment/microservice 9090:8080
$ http get localhost:9090

# make some changes to the WeatherResource
$ docker build -t realistic-cloud-native-devel .
$ telepresence --swap-deployment microservice --docker-run --rm -it realistic-cloud-native-devel

$ kubectl port-forward deployment/microservice 9090:8080
$ http get localhost:9090

# terminate Telepresence
$ kubectl port-forward deployment/microservice 9090:8080
$ http get localhost:9090
```

## Using Squash

- https://squash.solo.io/overview/

```bash
$ brew install solo-io/tap/squashctl
```

## Further References

- https://squash.solo.io/overview/
- https://github.com/ksync/ksync

## Maintainer

M.-Leander Reimer (@lreimer), <mario-leander.reimer@qaware.de>

## License

This software is provided under the MIT open source license, read the `LICENSE`
file for details.
