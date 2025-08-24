# =========================
# Build stage (Maven + JDK 21)
# =========================
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copia apenas pom.xml para cache de dependências
COPY main/pom.xml .
RUN mvn -B -q dependency:go-offline

# Copia código fonte
COPY ../app/src/main/src ./src

# Build do JAR (com camadas Spring Boot)
RUN mvn -B clean package -DskipTests

# =========================
# Runtime stage (JDK 21 slim)
# =========================
FROM eclipse-temurin:21-jre-jammy AS runtime

WORKDIR /app

# JVM otimizada para containers
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0 -XX:+UseG1GC -XX:+AlwaysPreTouch -Djava.security.egd=file:/dev/./urandom"

# Copia JAR do build
COPY --from=build /app/target/ecommerce-saas-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
