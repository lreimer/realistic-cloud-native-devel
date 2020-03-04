# Realistic Development of Cloud-native Applications

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

```bash
$ 
```


## Using kubefwd

```bash
$ brew install kubefwd

$ kubefwd svc -n default -l env=dev
```




## Maintainer

M.-Leander Reimer (@lreimer), <mario-leander.reimer@qaware.de>

## License

This software is provided under the MIT open source license, read the `LICENSE`
file for details.
