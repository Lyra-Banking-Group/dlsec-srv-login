# Usa uma imagem base com Java 17
FROM maven:3.9.5-eclipse-temurin-17 AS builder

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia apenas os arquivos necessários para o build para dentro do container
COPY pom.xml .
COPY src ./src

# Faz o build, ignorando os testes
RUN mvn clean package -DskipTests

# Etapa 2: apenas a execução
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copia o .jar da etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Define o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]