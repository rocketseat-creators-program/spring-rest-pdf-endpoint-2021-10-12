<img src="https://storage.googleapis.com/golden-wind/experts-club/capa-github.svg" />

# Criando relatórios personalizados em PDF com IText em sua aplicação Java

Nessa aula aprenderemos a construir um relatório personalizado em PDF, utilizando o iText 7, e disponibilizá-lo através de
uma requisição HTTP.

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Lombok](https://projectlombok.org/)
- [H2 Database](https://www.h2database.com/html/quickstart.html)
- [iText](https://itextpdf.com/en/resources/api-documentation)

## Como adicionar a dependência do iText na sua aplicação?

- pom.xml
    ```
    <dependency>
      <groupId>com.itextpdf</groupId>
      <artifactId>itext7-core</artifactId>
      <version>7.1.16</version>
      <type>pom</type>
    </dependency>
    ```

- gradle
    ````
    implementation 'com.itextpdf:itext7-core:7.1.16'
    ````

## Endpoints da aplicação

### Endpoints de Alunos (Students)
- Listar todos os alunos cadastrados na base
  ````
  curl --location --request GET 'localhost:8080/students'
  ````

- Gerar relatório PDF com a listagem dos alunos e suas respectivas idades
  ````
  curl --location --request GET 'localhost:8080/students/report'
  ````

### Endpoints de Escolas (Schools)
- Listar todos as escolas cadastradas na base
  ````
  curl --location --request GET 'localhost:8080/schools'
  ````

- Gerar relatório PDF com a listagem das escolas e suas respectivas quantidades de alunos
  ````
  curl --location --request GET 'localhost:8080/schools/report'
  ````
  
### Para testar todos os recursos da aplicação, utilize a collection do Postman abaixo:

- [Postman Collection](https://www.getpostman.com/collections/b46cab8571197072c555)

## Links úteis

- [Spring Initializr](https://start.spring.io/#!type=maven-project&language=java&platformVersion=2.5.4&packaging=jar&jvmVersion=11&groupId=com.example&artifactId=pdf&name=spring-rest-pdf-endpoint&description=Demo%20project%20for%20custom%20reports%20with%20IText&packageName=com.example.pdf&dependencies=web,data-jpa,lombok,validation,devtools,h2)

## Ambiente, recursos e requisitos necessários

- Java 11+
- Postman ou Insomnia
- Seu editor de código de preferência (No meu caso, Intellij)
- Vontade de aprender :D