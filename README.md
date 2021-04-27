# Weekly Challenge - VMware Buildpacks team

Problems:

1. [Boggle Mayhem](problems/1%20-%20Boggle%20Mayhem.pdf)

## 1. Using [Gradle](https://gradle.org/) to build and run the application

### Build the application

```shell
gradlew build
```

### Run the application

```shell
gradlew run
```

## 2. Using [Paketo Buildpacks](https://paketo.io/) to build and run the application using OCI Images

### Building the image

First, you have to select the default build, in this case is `paketobuildpacks/builder:base`

```shell
pack config default-builder paketobuildpacks/builder:base
```  

Now build the image with the following command:

```shell
pack build weekly-challenges-app
```

### Running the image

Run the following command to run the container with the created image and access it with bash.
```shell
docker run -it weekly-challenges-app bash
```

In the container, run the following command to start the application:
```shell
java com.vmware.bhenao.ProblemSolver
```
