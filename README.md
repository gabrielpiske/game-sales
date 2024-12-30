
# Game Sales

## Descrição

A aplicação **Game Sales** foi desenvolvida para simular uma plataforma de venda de jogos online. O sistema permite que os usuários se cadastrem, façam login, adicionem jogos ao carrinho e realizem pagamentos via **PIX** de forma lúdica. A aplicação é voltada para estudos e prática nas tecnologias **Spring Web/Security/JPA**.

### Funcionalidades:
- Cadastro de usuário.
- Login de usuário.
- Exibição de jogos disponíveis para compra.
- Adição de jogos ao carrinho.
- Cálculo do valor total do carrinho.
- Geração de QR Code para pagamento via **PIX**.
- Simulação de pagamento PIX.

## Tecnologias

A aplicação foi construída utilizando as seguintes tecnologias:

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat&logo=spring&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=flat&logo=thymeleaf&logoColor=white) 
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=flat&logo=html5&logoColor=white) 
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=flat&logo=css3&logoColor=white) 
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=flat&logo=javascript&logoColor=black)
![Bootstrap](https://img.shields.io/badge/Bootstrap-563D7C?style=flat&logo=bootstrap&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white)


## Estrutura do Projeto

O projeto está organizado da seguinte forma:

```
src/
│
├── main/
│   ├── java/
│   │   └── com/
│   │       └── gamesales/
│   │           ├── config/  # Configurações de Segurança
│   │           ├── controller/  # Controladores MVC
│   │           ├── model/       # Classes modelo (Jogo, Usuário, Carrinho)
│   │           ├── repository/  # Repositórios para acesso ao banco
│   │           ├── service/     # Serviços de lógica de negócio
│   │           └── GameSalesApplication.java  # Classe principal
│   │
│   └── resources/
│       ├── static/  # Arquivos estáticos (CSS, JS, imagens)
│       ├── templates/  # Templates Thymeleaf (páginas HTML)
│       └── application.properties  # Configurações do Spring Boot
│
└── pom.xml  # Arquivo de dependências do Maven
```

## Instalação

### Requisitos

- Java 17 ou superior.
- MySQL ou outro banco de dados relacional.
- Maven ou Gradle para gerenciamento de dependências.

### Passos para rodar o projeto:

1. Clone este repositório:

   ```bash
   git clone https://github.com/gabrielpiske/game-sales.git
   cd game-sales
   ```

2. Configure o banco de dados no arquivo `application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/game_sales_db
   spring.datasource.username=root
   spring.datasource.password=senha
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Compile o projeto com o Maven:

   ```bash
   mvn clean install
   ```

4. Inicie o servidor:

   ```bash
   mvn spring-boot:run
   ```

5. Acesse a aplicação em [http://localhost:8080/login](http://localhost:8080/login).
