# Utilisez une image Maven pour construire l'application
FROM maven:3.8.1-openjdk-17 AS build

# Copiez le pom.xml et les fichiers sources nécessaires
COPY pom.xml /build/
COPY src /build/src/

# Répertoire de travail dans l'image Maven
WORKDIR /build/

# Exécutez Maven pour construire l'application
RUN mvn clean package -DskipTests=true

# Utilisez une image OpenJDK 17 (ou une version appropriée) pour l'exécution
FROM openjdk:17-oracle

# Répertoire de travail dans le conteneur
WORKDIR /app

# Copiez le JAR de l'application Spring Boot depuis l'image de construction
COPY --from=build /build/target/hubgamers-0.0.1-SNAPSHOT.jar app.jar

# Exposer le port 8080 pour l'application Spring Boot
EXPOSE 8080

# Commande pour lancer l'application Spring Boot
CMD ["java", "-jar", "app.jar"]
