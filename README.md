# Desafio Sonda

| Data       | Autor | Comentários | Versão |
|------------| --- | --- | --- |
| 12/08/2024 | Douglas Oliveira  | Versão de lançamento | 1.0.0-RELEASE |

---

## Objetivo

O objetivo do sistema é ser a implementação do desafio de mover sonda.

---

## Inicializando o Sistema

### Via IDE

No caminho _br.com.desafio_ encontrar-se a classe [Application.java](./src/main/java/br/com/credsystem/Application.java), execute-a como uma aplicação Java via IDE.

### Via Maven

Construa a aplicação com __mvn clean install__, logo após, é possível executar a aplicação via linha de comando:
```java -jar desafio-1.0-SNAPSHOT.jar```

---

### Banco de Dodos H2
http://localhost:8080/h2-console

### Swagger

* http://localhost:8080/swagger-ui.html 
* http://localhost:8080/api-docs

### Actuator
* http://localhost:8080/actuator/info 
* http://localhost:8080/actuator/health

### Tecnologias

* Java 21
* Maven
* Spring boot 3.2.6
