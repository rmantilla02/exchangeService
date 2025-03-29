FROM eclipse-temurin:17-jdk-alpine

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR del microservicio al contenedor
COPY target/exchange-divisas-0.0.1-SNAPSHOT.jar exchange-divisas-0.0.1-SNAPSHOT.jar

# Expone el puerto en el que escucha el microservicio
EXPOSE 8080

# Comando para ejecutar el microservicio
ENTRYPOINT ["java", "-jar", "exchange-divisas-0.0.1-SNAPSHOT.jar"]
