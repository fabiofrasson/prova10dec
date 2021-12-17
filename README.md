# UniAmérica - Curso de Engenharia de Software



## Projeto de Implementação e Teste de Sistema de Informação Complexo



## Avaliação - Dezembro 21

Atividade: desenvolver um sistema de controle de estoque respeitando os seguintes requisitos:

- Cadastro de produto, contendo as informações: nome do produto, tipo do produto, fornecedor, quantidade em estoque, preço de compra e preço de venda. O sistema deverá permitir que sejam criados novos registros de produtos, editar produtos, remover produtos, aumentar e diminuir o estoque de um produto específico e listar todos os produtos existentes.
- Atentar-se aos atributos que deverão ser transformados em novas entidades.



#### Estrutura do Projeto

`/backend: ` contém todos os arquivos referentes ao Back-End da Aplicação;

`/doc: `Modelo de Entidade e Relacionamento;

`/frontend:` estrutura do Front-end da Aplicação.



#### Ferramentas necessárias no Projeto:

* [Java Development Kit versão 11](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html);

* [Apache Maven](https://maven.apache.org/);
* [PostgreSQL](https://www.postgresql.org/);
* [git](https://git-scm.com/);
* [pgAdmin](https://www.pgadmin.org/) (opcional).



#### Instruções para que o projeto seja inicializado:

1. Clonar o projeto via git `git clone https://github.com/fabiofrasson/prova10dec.git`;
2. Abrir a pasta `/backend`: em sua IDE de preferência;
3. Aguardar que as dependências do projeto sejam descarregadas;
4. Inicializar o `PostgreSQL` e, através do `pgAdmin` criar um banco de dados com o nome de `prova10dec`;
5. Alterar as configurações `spring.datasource.url`, `spring.datasource.username` e `spring.datasource.password` do arquivo pom.xml de acordo com suas credenciais do PostgreSQL local e porta escolhida durante a instalação. O arquivo pom.xml encontra-se em `/backend/src/main/resources`.

6. Rodar o projeto via terminal ou opção `Run` em sua IDE de preferência.