FROM openjdk:8-jdk-alpine
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /foo-service/lib
COPY ${DEPENDENCY}/META-INF /foo-service/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /foo-service
ENTRYPOINT ["java","-cp","foo-service:foo-service/lib/*","org.martseniuk.diploma.foo.FooApplication"]