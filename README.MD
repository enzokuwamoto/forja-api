# ⚔️ Forja API - Backend de Treinos Híbridos

> **"Assim como no fogo é forjado o aço, no calor dos campos de batalhas são formados os melhores soldados."**

O **Forja** é uma API RESTful robusta desenvolvida para gerenciamento e rastreamento de treinos de musculação e cardio. O objetivo é fornecer um backend sólido para aplicações fitness, permitindo que usuários registrem seu progresso, consultem histórico detalhado e analisem métricas de desempenho.

O sistema foi construído seguindo as melhores práticas de **Clean Code**, arquitetura em camadas e princípios **SOLID**, com foco em performance e integridade de dados.

---

## 🛠️ Tecnologias Utilizadas

O projeto utiliza uma stack moderna baseada no ecossistema Java:

* ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) **Java 21** (LTS)
* ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) **Spring Boot 3.x**
* ![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white) **MySQL 8+**
* **Persistência:** Spring Data JPA / Hibernate
* **Ferramentas:** Maven, Insomnia/Postman

---

## ✅ Funcionalidades Implementadas

### 1. Catálogo de Exercícios Inteligente (`/v1/catalog`)
Um dos diferenciais técnicos do projeto é o sistema de busca "Fuzzy" para exercícios.
* **Busca Resiliente:** Algoritmos implementados (Regex + Normalizer) ignoram acentos, espaços extras, hifens e erros de digitação.
    * *Exemplo:* Buscar por `supino inclinado` encontra `Supíno Inclinadö (Halteres)`.
* **Filtros Combinados:** Permite filtrar por **Grupo Muscular** (Enum) e **Nome** simultaneamente.
* **Carga em Lote:** Endpoint para popular o banco com centenas de exercícios via JSON. 
[Créditos](https://github.com/joao-gugel/exercicios-bd-ptbr/blob/main/exercises/exercises-ptbr-full-translation.json)


### 2. Gestão de Sessões de Treino (`/v1/session`)
* **Deep Insert (Transacional):** Salva o treino completo (Sessão + Exercícios + Séries + Cardio) em uma única transação atômica. Se uma parte falhar, nada é salvo.
* **Histórico do Atleta:** Listagem otimizada de todas as sessões de um usuário.
* **Cascade Delete:** Remoção inteligente que limpa logs órfãos (Sets, LiftingLogs) ao deletar uma sessão.

### 3. Usuários (`/v1/users`)
* Cadastro de perfil e gerenciamento básico de dados do atleta.

---

## 🚧 Em Desenvolvimento (WIP)

Estamos trabalhando ativamente nas seguintes features:

* **Validação & Tratamento de erros:** Bean Validation (@Valid) e Exceções (@ControllerAdvice)
* **OpenAPI / Swagger (Springdoc):** Instalar dependência, gerar uma página web para documentação e testar os end points.

---

## 🔮 Roadmap (Próximos Passos)

* [ ] **Testes Unitários:** Cobertura de testes com JUnit 5 e Mockito, focando na lógica de normalização de strings.
* [ ] **Segurança:** Implementação de **Spring Security + JWT** para autenticação e proteção de rotas.
* [ ] **Configuração de CORS**

---

## 🔌 Documentação da API (Endpoints Principais)

### 🏋️ Catálogo
| Método | Rota | Descrição |

| `GET` | `/v1/catalog` | Lista exercícios. Filtros opcionais: `?muscleGroup=PEITO&name=supino` |

| `POST` | `/v1/catalog/batch` | Carga inicial de exercícios (Admin). |

### 📝 Sessões de Treinamentos
| Método | Rota | Descrição |

| `POST` | `/v1/session` | Cria um novo treino completo. |

| `GET` | `/v1/session/user/{userId}` | Retorna o histórico de treinos do usuário. |

| `GET` | `/v1/session/{sessionId}` | Detalhes de um treino específico. |

| `DELETE` | `/v1/session/{sessionId}` | Deleta um treino e seus logs associados. |

---

## ☕ Como Rodar o Projeto

Você tem duas opções: rodar tudo via Docker (mais fácil) ou rodar em modo de desenvolvimento (para debugar na IDE).

### Opção 1: Modo "Pronto para Uso" (Docker Completo) 🐳
*Ideal para testar a aplicação sem instalar nada além do Docker.*

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/seu-usuario/forja.git](https://github.com/seu-usuario/forja.git)
    cd forja
    ```

2.  **Gere o executável (JAR):**
    ```bash
    mvn clean package -DskipTests
    ```

3.  **Suba a aplicação e o banco:**
    ```bash
    docker-compose up --build
    ```
    *O Docker irá baixar o MySQL, configurar as credenciais automaticamente e iniciar a API.*

4.  **Acesse:**
    A API estará rodando em: `http://localhost:8080`

---

### Opção 2: Modo Desenvolvimento (Híbrido) 🛠️
*Ideal para codar e testar mudanças em tempo real.*

Neste modo, rodamos apenas o Banco de Dados no Docker, e a Aplicação na sua IDE (IntelliJ/Eclipse).

1.  **Suba apenas o Banco de Dados:**
    No terminal, execute:
    ```bash
    docker-compose up mysql
    ```
    *(Isso garante que você tenha um MySQL rodando na porta 3306 pronto para receber conexões).*

2.  **Configure o `application.properties` (Apenas uma vez):**
    Certifique-se de que seu arquivo local aponta para `localhost` (já que o container expõe a porta para sua máquina):
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/db_forja?createDatabaseIfNotExist=true
    spring.datasource.username=root
    spring.datasource.password=12345
    spring.jpa.hibernate.ddl-auto=update
    ```

3.  **Execute a aplicação pela IDE:**
    Abra o projeto no IntelliJ e clique em **Run** na classe `ForjaApplication`.
---

## 🤝 Contribuição

Este projeto é um portfólio pessoal de desenvolvimento Backend. Sugestões, Issues e Pull Requests são muito bem-vindos!

---

**Desenvolvido com 💪 e Java.**