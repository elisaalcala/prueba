# Etapa 1: Construcción de la aplicación
# Usar una imagen base oficial de Maven con JDK 17 para construir la aplicación
FROM maven:3.8.5-openjdk-17 AS builder

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo pom.xml y el archivo pom.lock primero para aprovechar la caché Docker
COPY pom.xml ./

# Descargar las dependencias
RUN mvn dependency:resolve

# Copiar el resto del código de la aplicación y construirla
COPY src ./src
RUN mvn package -DskipTests

# Etapa 2: Imagen ligera para ejecutar la aplicación
# Usar una imagen base oficial de OpenJDK 17 para el runtime
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo JAR desde el contenedor de construcción
COPY --from=builder /app/target/*.jar /app/myapp.jar

# Exponer el puerto en el que la aplicación correrá
EXPOSE 8443

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "/app/myapp.jar"]
