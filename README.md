# Projeto de Gestão da Mottu

📌 Nota: Projeto desenvolvido para fins acadêmicos na disciplina de Desenvolvimento com Java e Spring Boot.

Este é um sistema desenvolvido em Spring Boot para o cadastro e gerenciamento de motos, funcionários e filiais da Mottu.

A aplicação permite realizar CRUD completo (criação, listagem, edição e exclusão) para todas as entidades do domínio, garantindo controle centralizado e eficiente das informações.

Além disso, conta com recursos de autenticação via Google e GitHub e integração com banco de dados SQL Server.

## Índice

- [Integrantes](#integrantes)
- [Funcionalidades](#funcionalidades)
- [Como Rodar o Projeto](#como-rodar-o-projeto)

## Integrantes

RM555679 - Lavinia Soo Hyun Park

## Funcionalidades

- Cadastro de filiais, motos e funcionários
- Integração com SQL Server via Spring Data JPA
- Autenticação com Google e GitHub usando Spring Security OAuth2
- Relacionamentos entre entidades (ex.: motos vinculadas a uma filial, funcionário associado a filial)

## Como Rodar o Projeto

1. Requisitos
- Java 17 (https://adoptium.net/)
- Maven 3.9+ (https://maven.apache.org/)
- Azure SQL Database
- IDE recomendada: IntelliJ IDEA ou Eclipse

2. Configuração da conexão com o Banco de Dados

Primeiro, prepare o seu Banco de Dados. Você tem duas opções:

**OPÇÃO A - Criar seu Azure SQL Database**

1. Acesse o [Portal Azure](https://portal.azure.com/#home)
2. Abra o Cloudshell
3. Rode os seguintes comandos:
```
az group create --name rg-unhaeng-bcosql --location brazilsouth
```
```
az sql server create \
-l brazilsouth -g rg-unhaeng-bcosql -n sqlserver-unhaeng \
-u USERNAME -p PASSWORD \
--enable-public-network true
```
***Mude os campos "USERNAME" e "PASSWORD" pelos dados que você deseja***

***A senha deve conter: letra maiuscula, letra minuscula, numero e caractere especial (opte por usar "!")***
```
az sql db create \
-g rg-unhaeng-bcosql -s sqlserver-unhaeng -n unhaengdb \
--service-objective Free --backup-storage-redundancy Local \
--zone-redundant false
```
```
az sql server firewall-rule create \
-g rg-unhaeng-bcosql -s sqlserver-unhaeng -n AllowAll \
--start-ip-address 0.0.0.0 --end-ip-address 255.255.255.255
```

**OPÇÃO B - Rodar Localmente usando Docker**

a) Instale o [Docker Desktop](https://www.docker.com/products/docker-desktop/)

b) Abra um Terminal e rode este comando para criar um container com SQL Server 2022:
```
docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=StrongPassw0rd!" ^
   -p 1433:1433 --name sqlserver ^
   -d mcr.microsoft.com/mssql/server:2022-latest
```
(no Linux/Mac o ```^``` vira ```\``` para quebrar linhas)
Usuário padrão: ```SA```
Senha: ```StrongPassw0rd!``` (pode trocar se quiser)
Porta: ```1433```

c) Acesse o Banco com [Azure Data Studio](https://learn.microsoft.com/pt-br/azure-data-studio/download-azure-data-studio?tabs=win-install%2Cwin-user-install%2Credhat-install%2Cwindows-uninstall%2Credhat-uninstall)
- Server: localhost,1433
- Login: sa
- Senha: StrongPassw0rd!

d) Crie um banco para o projeto
```
CREATE DATABASE mottu;
```
e) Configure no application-prod.properties do repositório (não esqueça de antes clonar o repositório)
```
DB_URL=jdbc:sqlserver://localhost:1433;database=mottu;encrypt=false;trustServerCertificate=true;
DB_USER=sa
DB_PASSWORD=StrongPassw0rd! (mude para a senha que voce definiu)
```

3. Configuração do OAuth2 (Google/GitHub)

**Criando Credenciais do GOOGLE**

a) Acesse [Google Cloud Console](https://console.cloud.google.com/welcome/new)

b) Crie um projeto ou escolha um já existente

c) Vá em APIs e Serviços > Credenciais > Criar Credenciais > ID do Cliente OAuth

d) Selecione Aplicativo da Web como tipo

e) Adicione o URI de redirecionamento autorizado (em dev normalmente será http://localhost:8080/login/oauth2/code/google)

f) Salve e copie o Client ID e o Client Secret

**Criando Credenciais do GITHUB**

a) Vá até [Configurações do GitHub → Developer settings → OAuth Apps](https://github.com/settings/developers)

b) Clique em New OAuth App

c) Preencha os campos:
- Homepage URL: http://localhost:8080
- Authorization callback URL: http://localhost:8080/login/oauth2/code/github

d) Depois de salvar, copie o Client ID e o Client Secret

e) No application.properties, insira as informações no lugar do respectivo placeholder ```${placeholder}```:
```
spring.security.oauth2.client.registration.google.client-id=${GOOGLE_CLIENT_ID}
spring.security.oauth2.client.registration.google.client-secret=${GOOGLE_CLIENT_SECRET}
spring.security.oauth2.client.registration.github.client-id=${GITHUB_CLIENT_ID}
spring.security.oauth2.client.registration.github.client-secret=${GITHUB_CLIENT_SECRET}
```

4. Executando o Projeto (Terminal)

Instale dependências:
```
mvn clean install
```
Inicie o servidor:
```
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod;
```
*A aplicação utiliza o profile prod para carregar as credenciais de banco e OAuth. Certifique-se de rodar sempre com o parâmetro ```-Dspring-boot.run.profiles=prod```*

Acesse a aplicação (Copie a URL no navegador)

http://localhost:8080

Agora desfrute da aplicação!
