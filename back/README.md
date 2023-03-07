# Serviço de gerenciamento de precatório

## O que é este projeto?

Este é um serviço de envio de precatorio nos padrões da PDPJ, e serve para guiar os 
desenvolvedores sobre a aplicação das boas práticas e padrões para o desenvolvimento 
de novos serviços para a PDPJ.

Este projeto aplica o padrão MVC tradicional, e utiliza as dependências `pdpj-commons`
e `pdpj-starter`, as quais estão disponíveis no repositório de artefatos do CNJ - Nexus (vide
arquivo `.m2\settings.xml`) (para solicitar as credenciais de acesso do repositório de artefatos, 
encaminhe email para [cadastrodeusuariospje@cnj.jus.br](mailto:cadastrodeusuariospje@cnj.jus.br)).

Recomenda-se utilizar este projeto como raíz para o desenvolvimento de novos microserviços 
para a plataforma, alterando, conforme o caso, os nomes de classes, pacotes e outros 
artefatos que façam referência ao nome do serviço.

> TIP: Nos diversos arquivos de configuração do projeto, foi utilizado a expressão `precatorio`
para referenciar o nome do projeto. Substitua essa expressão pelo nome real do seu projeto.

## O que eu devo modificar

Em princípio, os únicos pacotes que necessitarão de grande personalização são os pacotes
`controllers`, `models`, `repositories` e `services`, onde serão colocadas as classes responsáveis
por expor a API REST do serviço, as classes de entidades, DTOs e _mappers_, as classes de acesso
ao banco de dados (no formato Spring Data JPA), e as classes de serviço, responsáveis pela 
implementação das regras negociais específicas da aplicação.

No mais, deve-se modificar os arquivos de configuração (`yamls`, `pom.xml`, etc), de acordo com
as necessidades de projeto, bem como deve-se fazer os ajustes necessários nas classes do pacote
`configurations`.

## Pilha de tecnologias

Este serviço, como potencialmente qualquer serviço a ser desenvolvido para a PDPJ, se utiliza
das seguintes ferramentas/tecnologias:

* **Keycloak** - ferramenta _open source_ de controle de acessos e identidade, para autenticação de usuários;
* **PDPJ Discovery** - serviço de descoberta da PDPJ, que se utiliza de um Eureka Server;
* **PDPJ Gateway** - serviço de _gateway_ da PDPJ, que se utiliza da ferramenta Netflix Zuul;
* **PostgreSQL** - sistema gratuito de gerenciamento de bancos de dados relacionais;
* **RabbitMQ** - serviço de mensageria, para comunicação assíncrona;
* **Redis** - serviço opcional, utilizado na PDPJ precipuamente para cache;

Para facilitar a montagem do ambiente local de desenvolvimento, o projeto já vem acompanhado 
de um arquivo `docker-compose.yaml`, que sobe em contêineres `Docker` toda a pilha utilizada 
pelo projeto. Para tanto, é necessário estar instalado na máquina o `Docker` e o `docker-compose`. 

Para subir a pilha, execute o seguinte comando a partir da pasta raíz do projeto:
```bash
docker-compose up -d
```
> Antes de executar o docker-compose, é necessário fazer login no repositório de artefatos do CNJ - Nexus, por meio do comando `docker login registry.cnj.ju.sbr`, o qual solicitará as credenciais mencionadas na primeira seção.

> Para o `postgreSQL` funcionar corretamente no Windows, é necessário criar um volume local. Assim,
antes de executar o docker-compose, crie o volume com o seguinte comando: `docker volume create --name postgresql-volume --driver local`, e adeque o arquivo `docker-compose.yml` para apontar para o volume criado. Para maiores detalhes, vide https://kingsor.github.io/2019/03/23/using-postgres-with-docker-on-windows/.

Ao executar o compose temos acesso aos seguintes serviços:

|Serviço |Tecnologia |Url |
|--------|-----------|----|
|keycloak |Keycloak |http://localhost:8280 |
|pdpj-discovery-service |Netflix Eureka |http://localhost:8761 |
|pdpj-gateway-service |Netflix Zuul |http://localhost:8180 |
|message-broker |RabbitMQ |http://localhost:5672 |
|message-broker-management |RabbitMQ |http://localhost:15672 |
|redis |Redis |http://localhost:6379 |

Para encerrar a execução dos serviços, execute:
```bash
docker-compose down
```

> NOTE: No Windows, caso haja dificuldade com a comunicação entre os serviços via _gateway_, execute-o manualmente, como explicado mais abaixo, e não via Docker.

### Executando manualmente a pilha de Serviços da PDPJ

Alternativamente, você também pode executar alguns dos serviços da pilha manualmente, clonando os projetos abaixo e executando-os conforme instruções de seus arquivos README.md:

|Serviço |Git |
|--------|----|
|pdpj-discovery-service | https://git.cnj.jus.br/pdpj/infraestrutura/discovery |
|pdpj-gateway-service | https://git.cnj.jus.br/pdpj/infraestrutura/gateway |

### Configurando o Keycloak

Acesse o Keycloack em localhost:8280

Clicar em administration console
Logar com admin/admin
Passar o mouse sobre "Master" e clicar em "Add realm"
Adicionar realm "pdpj"
Com realm "pdpj" selecionado, clicar em "Clients" > "Create"
Preencha o formulário com:

Em Client ID, preencha "sniper-frontend"
Em Client Protocol, selecione "openid-connect"
Clique em "Save" e continue na página do client criado, para editar os
campos abaixo:
Digite * em "Valid Redirect URIs" e clique no botão "+"
Digite * em "Web Origins" e clique no botão "+"
Clique em "Save"


Com realm "pdpj" selecionado, clicar em "Clients" > "Create"

Em Client ID, preencha "sniper-backend"
Em Client Protocol, selecione "openid-connect"
Clique em "Save" e continue na página do client criado, para editar os
campos abaixo:
Em Access Type, selecione "bearer-only"
Clique em "Save"
Ainda na tela desse client, clique em "Credentials" e depois no botão
"Regenerate Secret"
Copie o secret que aparece no campo "Secret" do formulário


Altere o arquivo .env para conter:


export KEYCLOAK_CLIENT_SECRET_KEY=<secret-copiada> (sem os < e >)


Ainda com o realm "pdpj", clique em "Users" > "Add user"
Preencha o formulário com:

Em Username, preencha seu CPF ou "11111111111"
Em "Email verified", marque "ON"
Clique em "Save"
Ainda na tela desse user, preencha os campos:
Em "Email", preencha "user1@example.com"
Em "First Name", preencha "João"
Em "Last Name", preencha "da Silva"
Clique em "Save"


Na tela do usuário criado, clicar em "Credentials", preencher uma senha e a
confirmação de senha, desmarcar "temporary password" e clicar em "Set"
Para que a mudança de secret do backend faça efeito, execute docker compose down, source .env e docker compose up

## Flyway

Para a criação das tabelas, _sequences_ e outros objetos no banco de dados, bem como sua atualização incremental,
utiliza-se a biblioteca _flyway_, que permite a execução de _scripts_ SQL no momento da subida da aplicação.

O _Flyway_ funciona como um mecanismo de versionamento do banco de dados, onde cada versão corresponde a um _script_
SQL, os quais são executados sequencialmente pela ferramenta conforme o padrão de nomenclatura por ela definido.

Recomenda-se que cada _script_ SQL seja idempotente, ou seja, que ele possa ser executado quantas vezes for necessário,
sem lançar erros ou tornar a base inconsistente. Para tanto, recomenda-se utilizar a cláusula `IF NOT EXISTS` em todos
os comandos que a suportem, bem como suceder qualquer comando de `INSERT` com a cláusula `ON CONFLICT DO NOTHING`.

Um exemplo de _scripts_ de migração do _flyway_ acompanham este projeto na pasta `src/main/resources/db/migration`.

> Para maiores detalhes, vide https://flywaydb.org/

## Deploy automático da aplicação

Para fazer uso do mecanismo de _deploy_ automático do seu projeto para os ambientes de homologação e produção
da PDPJ, solicite ao mentor técnico do projeto a configuração da _pipeline_ de CI/CD.

Feito isso, sempre que houver um `commit` na `branch master` o GitLab irá disparar automaticamente a _pipeline_, fazendo _deploy_ no ambiente de homologação.

Caso seja lançada uma `tag` de versão do projeto, o GitLab fará _deploy_ em produção.

> INFO: O `Dockerfile` deste projeto, que será executado pela _pipeline_, presume que o arquivo gerado pelo processo de _build_ possui um nome pré-definido. Para tanto, no arquivo `pom.xml` foi adicionada a _tag_ `finalName`:
```xml
    <build>
        <finalName>precatorio</finalName>
        ...
    </build>
```

> Para que a aplicação execute com sucesso a partir de quaisquer desses ambientes, é necessário que sejam criadas as `secrets` utilizadas pelo projeto no respectivo ambiente de homologação ou produção, bem como é necessário que o `banco de dados`, com as credenciais ordinárias e para uso do `flyway`, sejam criados pela equipe de infraestrutura do CNJ. Solicite ao mentor técnico do seu projeto a criação dessas dependências.

## Integração com o Marketplace

Este projeto já vem, por padrão, com todas as configurações setadas para a integração com o 
Marketplace da PDPJ funcionar automaticamente, mediante a configuração no arquivo `application.yaml`
e a exposição dos _endpoints_ esperados pelo Marketplace.

## Integração com o Serviço de Notificações

Para se integrar com o serviço de notificações da PDPJ, a aplicação deve lançar os eventos negociais
relevantes que serão oportunamente cadastrados naquele serviço.

Para tanto, basta chamar o método utilitário `sendMessage` localizado na classe `MensageriaService`.

## Como executar este projeto?

Este projeto foi construído com Spring Boot e pode ser executado através do comando:
```bash
mvn spring-boot:run
```

## Como executar um container do projeto?

O primeiro passo é compilar o .jar da aplicação:
```bash
mvn clean package
```

Este projeto contém um dockerfile que pode ser construído com o seguinte comando
```bash
sudo docker build -t referencia:local .
```

Para executar o container execute o seguinte comando:
```bash
sudo docker run -it -d -p 8080:8080 referencia:local
```

O serviço estará disponível em http://localhost:8080
