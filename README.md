# TaskManagement

O **TaskManagement** é uma aplicação desenvolvida em Java com Spring Boot, projetada para gerenciar usuários e suas respectivas tarefas. O sistema permite criar, editar, visualizar e excluir registros de usuários, facilitando a organização e o acompanhamento das tarefas associadas a cada um.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.0.0
- MySQL
- Maven
- Lombok

## Pré-requisitos

Antes de compilar a aplicação, certifique-se de ter os seguintes itens instalados em seu ambiente:

- [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [MySQL](https://dev.mysql.com/downloads/mysql/)
- [DBeaver](https://dbeaver.io/download/)

## Configuração do Banco de Dados

1. **Crie um banco de dados no MySQL** chamado `task_management`.
2. **Configure o arquivo `application.properties`** para conectar ao seu banco de dados:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/taskmanagement
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.datasource.username=root
   spring.datasource.password=root


   ## Visualizando o Banco de Dados com DBeaver
1. **Abra o DBeaver e crie uma nova conexão:**
   - Clique em "Nova Conexão" e selecione "MariaDB".
   - Preencha os detalhes da conexão (Host: localhost, Porta: 3307, Banco de Dados: taskmanagement, Usuário: root, Senha: root).
   - Clique em "Testar Conexão" para garantir que tudo está configurado corretamente e depois em "Concluir".
2. **Navegue até o banco de dados e visualize as tabelas user e task para verificar as informações armazenadas.**
## Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request.
