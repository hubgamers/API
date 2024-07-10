# Utilisez une image OpenJDK 17 (ou une version appropriée)
FROM openjdk:17-oracle

# Répertoire de travail dans le conteneur
WORKDIR /app

# Copier le jar de l'application Spring Boot dans le conteneur
COPY target/hubgamers.jar app.jar

# Exposer le port 8080 pour l'application Spring Boot
EXPOSE 8080

# Commande pour lancer l'application Spring Boot
CMD ["java", "-jar", "app.jar"]
