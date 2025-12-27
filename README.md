# ITAU Password validate

Este projeto foi desenvolvido como parte do processo seletivo do Itaú, com o objetivo de demonstrar minhas habilidades em raciocínio lógico, arquitetura de software e qualidade de código.

Optei pela **Clean Architecture** por ser o padrão com o qual tenho maior familiaridade e que aplico no dia a dia. Durante a implementação, foquei nos princípios de **SOLID** e **Clean Code**.
Acredito que pensar na facil manutençào e na facilidade de leitura para novos colaboradores é tão importante quanto a funcionalidade em si.

## O Problema
O desafio consistiu em construir uma API REST que valida se uma senha atende aos seguintes critérios:

- Nove ou mais caracteres
- Ao menos 1 dígito
- Ao menos 1 letra minúscula
- Ao menos 1 letra maiúscula
- Não permitir espaços em branco
- Ao menos 1 caractere especial
    - Considere como especial os seguintes caracteres: !@#$%^&*()-+
- Não possuir caracteres repetidos dentro do conjunto

**Input:** Uma senha (string).
**Output:** Um boolean indicando se a senha é válida.

## Solução e Decisões Técnicas
Toda a lógica de validação foi implementada conforme os requisitos descritos.

**Input:** O input é enviado via JSON no corpo da requisição (Body) utilizando o método **POST**.
Seguindo boas práticas de design de API, métodos como POST, PUT e PATCH devem trafegar dados sensíveis ou complexos no payload, e não na URL.

> { <br>
>   &nbsp;&nbsp;&nbsp;&nbsp; "password" : "AedfS$!32" <br>
>}

**Output:** O retorno também é um objeto JSON. Essa abordagem facilita a evolução da API: caso seja necessário incluir novos campos de retorno no futuro (como uma mensagem de erro detalhada), não quebramos a integração com os clientes existentes.

> { <br>
>   &nbsp;&nbsp;&nbsp;&nbsp; "isValid" : true <br>
>}

## Códigos HTTP

### 200 - OK
Retornado quando a requisição é processada com sucesso e a validação foi executada (independente se o resultado é `true` ou `false`).

### 412 - Unprocessable Content
Utilizei o status 412 para casos onde o payload está correto (é um JSON válido), mas semanticamente incorreto (ex: senha vazia ou nula). Embora o 400 (Bad Request) seja comum, o 412 é mais específico para erros de validação de negócio.

## Tecnologias Utilizadas

*   **Java 17** (ou versão superior)
*   **Spring Boot**
*   **Gradle** (Gerenciador de dependências)
*   **Lombok** (Para redução de boilerplate code)

## Pré-requisitos

Antes de começar, certifique-se de ter instalado em sua máquina:

*   Java JDK 17+
*   Git

## 🚀 Começando

Siga estas instruções para obter uma cópia do projeto e executá-lo localmente para fins de desenvolvimento e teste.

### 1. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
```

### 2. Configuração

O arquivo de propriedades da aplicação (`application.yaml`) possui configurações parametrizadas via variáveis de ambiente. Isso permite alterar as regras de validação sem necessidade de recompilar o código.

- **LENGTH**: Tamanho mínimo da senha (Padrão: 9).
- **LOWER_CASE_NUMBER**: Quantidade mínima de letras minúsculas (Padrão: 1).
- **UPPER_CASE_NUMBER**: Quantidade mínima de letras maiúsculas (Padrão: 1).
- **DIGIT_NUMBER**: Quantidade mínima de números (Padrão: 1).
- **SPECIAL_CHARACTER**: Conjunto de caracteres especiais permitidos (Padrão: `!@#$%^&*()-+`).

```yaml
itau.password:
  business:
    rules:
      password:
        length: ${LENGTH:9}
        lower.case.number: ${LOWER_CASE_NUMBER:1}
        upper.case.number: ${UPPER_CASE_NUMBER:1}
        digit.number: ${DIGIT_NUMBER:1}
        special.character: ${SPECIAL_CHARACTER:!@#$%^&*()-+}
```

### 3. Compilar o projeto

Utilize o Gradle Wrapper para garantir que está usando a versão correta do Gradle:

**Linux/macOS:**
```bash
./gradlew build
```

**Windows:**
```bash
gradlew build ou gradlew.bat build
```
ou
```bash
gradlew.bat build
```

### 4. Executar a aplicação

Após a compilação, você pode rodar a aplicação via linha de comando:

```bash
./gradlew bootRun
```

A aplicação estará disponível em: `http://localhost:8080`

## Documentação da API

A documentação Swagger/OpenAPI está disponível em:

*   Swagger UI: `http://localhost:8080/swagger-ui.html`
*   OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Testes

Para executar os testes unitários e de integração:

**Linux/macOS:**
```bash
./gradlew test
```
**Windows (Prompt de Comando ou PowerShell):**
```powershell
.\gradlew.bat test
```

## Execução

Além do Swagger o scropt abaixo pode ser via linha de comando ou importado no Postman

```curl
curl --location 'http://localhost:8080/itau/password/validate' \
--header 'Content-Type: application/json' \
--data '{
"password": "AbTp9!fok"
}'
```