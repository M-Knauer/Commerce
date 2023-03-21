# Commerce
Commerce é um sistema backend para gerenciamento de uma loja virtual, implementado em Java 17 com o framework Spring Boot 2.7.3. O sistema oferece diversas funcionalidades para usuários admin e clientes, como adição, atualização e remoção de produtos, busca de produtos, compra de produtos, entre outras.

O sistema possui diversas entidades relacionadas entre si, como Product, Order, User, Payment, Category, Role e OrderItem, que são gerenciadas pelo sistema utilizando a tecnologia JPA com Hibernate. O banco de dados utilizado é o H2, que é um banco de dados em memória.

O projeto conta com um tratamento de exceções customizado, que ajuda a identificar e tratar erros de forma mais eficiente. Além disso, o sistema utiliza validação para garantir a integridade dos dados inseridos pelo usuário.

Para garantir a segurança das operações, o sistema utiliza o Spring Security e o OAuth 2, com a geração de tokens JWT. Dessa forma, apenas usuários autenticados podem acessar as funcionalidades do sistema que requerem autorização.

O sistema também utiliza JPQL para consultas personalizadas no banco de dados, permitindo uma maior flexibilidade e eficiência nas operações.

No geral, o projeto Commerce é uma aplicação backend bem estruturada e completa para gerenciamento de uma loja virtual, que utiliza diversas tecnologias modernas para garantir uma experiência segura e eficiente aos usuários.
