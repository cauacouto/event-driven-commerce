<div align="center">

# 🛒 E-commerce Microservices

> Uma arquitetura de **microsserviços** escalável e distribuída para e-commerce corporativo

[![License MIT](https://img.shields.io/badge/License-MIT-4CAF50?style=flat-square&logo=opensourceinitiative)](LICENSE)
[![Java 21](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=java&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot 4](https://img.shields.io/badge/Spring%20Boot-4-2bbc8a?style=flat-square&logo=springboot&logoColor=white)](https://spring.io/)
[![Status](https://img.shields.io/badge/Status-Under%20Development-FFC107?style=flat-square)]()
[![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?style=flat-square&logo=docker&logoColor=white)]()

<p>
  <a href="#-quick-start"><strong>Quick Start</strong></a> •
  <a href="#-arquitetura"><strong>Arquitetura</strong></a> •
  <a href="#-features"><strong>Features</strong></a> •
  <a href="#-api"><strong>API</strong></a> •
  <a href="#-contribuição"><strong>Contribuição</strong></a>
</p>

</div>

---

## 🎯 Visão Geral

Um projeto de **referência** para construir sistemas distribuídos com microsserviços. Implementamos padrões e boas práticas utilizadas por **grandes empresas de tech**, com foco em escalabilidade, resiliência e performance.

```
📦 Múltiplos serviços isolados
🔗 Comunicação síncrona e assíncrona
🚀 Auto-discovery de serviços
💾 Cache distribuído
📊 Event-driven architecture
🛡️ API Gateway centralizado
```

---

## 🏗️ Arquitetura

<table>
<tr>
<td width="50%">

```
┌──────────────────────┐
│    👤 Cliente        │
└──────────┬───────────┘
           │
     ┌─────▼──────┐
     │ 🌐 Gateway │
     │  (8080)    │
     └─────┬──────┘
           │
    ┌──────┴──────────┬─────────┐
    │                 │         │
 ┌──▼──┐         ┌───▼───┐  ┌─▼────┐
 │📦P  │         │📦E    │  │💳Pay │
 │(80) │         │(8082) │  │(8083)│
 └──┬──┘         └───┬───┘  └─┬────┘
    │                │       │
    └────────┬───────┴───────┘
             │
        ┌────▼────────┐
        │ 🔍 Eureka   │
        │  (8761)     │
        └─────────────┘
```

</td>
<td width="50%">

### 🧩 Componentes

| Serviço | Porta | Status |
|---------|-------|--------|
| **🌐 Gateway** | 8080 | ✅ |
| **🔍 Eureka** | 8761 | ✅ |
| **📦 Produto** | 8081 | ✅ |
| **📦 Estoque** | 8082 | ✅ |
| **💳 Pagamento** | 8083 | 🚧 |

### 🗄️ Infraestrutura

```
├─ 🗄️  MySQL (Dados)
├─ 🔴 Redis (Cache)
├─ 🐰 RabbitMQ (Eventos)
└─ 🐳 Docker Compose
```

</td>
</tr>
</table>

---

## ⚡ Quick Start

### 📋 Pré-requisitos

```bash
✓ Java 24+  ✓ Maven 3.8+  ✓ Docker  ✓ Git
```

### 🚀 Iniciar em 5 minutos

```bash
# 1️⃣ Clone
git clone https://github.com/seu-usuario/ecommerce-microservices.git
cd ecommerce-microservices

# 2️⃣ Inicie a infraestrutura
docker-compose up -d

# 3️⃣ Compile
mvn clean install -DskipTests

# 4️⃣ Execute (em 4 terminais diferentes)
cd eureka-server && mvn spring-boot:run
cd api-gateway && mvn spring-boot:run
cd produto-service && mvn spring-boot:run
cd estoque-service && mvn spring-boot:run

# ✅ Pronto! Acesse http://localhost:8761
```

> 💡 **Dica:** Use `./start-all.sh` para iniciar todos os serviços automaticamente

---

## ✨ Features

<table>
<tr>
<td>

### 📦 Produto Service
- ✅ CRUD completo
- ✅ Cache com Redis
- ✅ Validação de dados
- ✅ Profiles (dev/prod)

</td>
<td>

### 📦 Estoque Service
- ✅ Gerenciar quantidade
- ✅ Validação cross-service
- ✅ OpenFeign integration
- ✅ Real-time updates

</td>
</tr>
<tr>
<td>

### 🌐 API Gateway
- ✅ Roteamento inteligente
- ✅ Load balancing
- ✅ Rate limiting
- ✅ Request tracing

</td>
<td>

### 🔍 Service Discovery
- ✅ Eureka Server
- ✅ Auto-registration
- ✅ Health checks
- ✅ Load balance aware

</td>
</tr>
</table>

### 🚧 Em Breve

```
🔄 Circuit Breaker (Resilience4j)
📊 Distributed Tracing (Jaeger)
📈 Metrics & Monitoring (Prometheus)
🔐 OAuth2 & JWT
🧪 Testes E2E
⚙️ CI/CD Pipeline
```

---

## 📡 API Reference

### 🔌 Base URL
```
http://localhost:8080/api
```

### 📦 Produtos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/produtos` | Listar todos |
| GET | `/produtos/{id}` | Obter um |
| POST | `/produtos` | Criar novo |
| PUT | `/produtos/{id}` | Atualizar |
| DELETE | `/produtos/{id}` | Deletar |

### 📊 Estoque

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/estoque` | Listar estoque |
| GET | `/estoque/{id}` | Consultar produto |
| POST | `/estoque` | Criar registro |
| PUT | `/estoque/{id}` | Atualizar qtd |

### 📝 Exemplo de Requisição

```bash
# Criar produto
curl -X POST http://localhost:8080/api/produtos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "MacBook Pro",
    "descricao": "Laptop powerful para devs",
    "preco": 15999.99,
    "ativo": true
  }'

# Listar com pagination
curl "http://localhost:8080/api/produtos?page=0&size=10"

# Buscar por ID
curl http://localhost:8080/api/produtos/1
```

---

## 🗂️ Estrutura do Projeto

```
ecommerce-microservices/
│
├── 📁 eureka-server/
│   └── Serviço de descoberta dinâmica
│
├── 📁 api-gateway/
│   └── Ponto de entrada centralizado
│
├── 📁 produto-service/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── config/
│
├── 📁 estoque-service/
│   ├── controller/
│   ├── service/
│   ├── client/ (OpenFeign)
│   ├── entity/
│   └── config/
│
├── 📁 pagamento-service/
│   └── (em desenvolvimento)
│
├── docker-compose.yml
├── pom.xml (parent)
├── .gitignore
├── LICENSE
└── README.md
```

---

## 🔄 Data Flow

```
Cliente
  │
  ├──> API Gateway
  │      │
  │      ├──> Produto Service
  │      │      │
  │      │      └──> Redis Cache
  │      │
  │      ├──> Estoque Service (chama Produto via OpenFeign)
  │      │      │
  │      │      └──> Validação
  │      │
  │      └──> Pagamento Service
  │             │
  │             └──> RabbitMQ (evento)
  │                    │
  │                    └──> Estoque (atualizar)
  │
  └──> Response
```

---

## 🛠️ Tecnologia Stack

| Categoria | Tecnologias |
|-----------|-------------|
| **Backend** | Java 24 • Spring Boot 4 • Spring Cloud (Gateway, Eureka, OpenFeign) • Spring Data JPA • Lombok |
| **Infraestrutura** | MySQL 8 • Redis 7 • RabbitMQ 3.12 • Docker • Docker Compose |
| **Build & Deploy** | Maven 3.8+ • Git • GitHub Actions (em breve) |

---

## 📊 Padrões Implementados

| Padrão | Descrição | Status |
|--------|-----------|--------|
| **Service Discovery** | Eureka para descoberta dinâmica | ✅ |
| **API Gateway** | Roteamento centralizado | ✅ |
| **Database per Service** | Banco isolado por microsserviço | ✅ |
| **Synchronous Communication** | OpenFeign para chamadas HTTP | ✅ |
| **Asynchronous Communication** | RabbitMQ para eventos | 🚧 |
| **Circuit Breaker** | Resilience4j | 🚧 |
| **Distributed Tracing** | Jaeger/Spring Cloud Sleuth | 🚧 |
| **Caching** | Redis cache-aside pattern | ✅ |

---

## 🧪 Testes & Qualidade

```bash
# Testes unitários
mvn test

# Testes de integração
mvn verify

# Cobertura de código
mvn test jacoco:report

# Análise estática
mvn sonar:sonar
```

---

## 📚 Documentação

| Documento | Descrição |
|-----------|-----------|
| [ARCHITECTURE.md](docs/ARCHITECTURE.md) | Detalhes de design |
| [API.md](docs/API.md) | Documentação completa de API |
| [DEPLOYMENT.md](docs/DEPLOYMENT.md) | Guia de produção |
| [CONTRIBUTING.md](CONTRIBUTING.md) | Como contribuir |

---

## 🚀 Deployment

### Docker Compose (Desenvolvimento)
```bash
docker-compose up -d
```

### Kubernetes (Produção)
```bash
kubectl apply -f k8s/
```

---

## 🔗 Links Úteis

- 📖 [Spring Cloud Docs](https://spring.io/projects/spring-cloud)
- 🏗️ [Microservices Patterns](https://microservices.io/patterns/index.html)
- 🐳 [Docker Docs](https://docs.docker.com/)
- 🐰 [RabbitMQ Guide](https://www.rabbitmq.com/getstarted.html)
- 🔴 [Redis Docs](https://redis.io/documentation)

---

## 🤝 Contribuindo

### Quer contribuir? Veja como! 

```bash
# 1. Fork o repositório
# 2. Crie sua feature branch
git checkout -b feature/amazing-feature

# 3. Commit suas mudanças
git commit -m 'feat: add amazing feature'

# 4. Push para a branch
git push origin feature/amazing-feature

# 5. Abra um Pull Request
```

Leia [CONTRIBUTING.md](CONTRIBUTING.md) para mais detalhes.

---

## 📝 Licença

Distribuído sob a licença MIT. Veja [LICENSE](LICENSE) para mais informações.

---

## 👨‍💻 Autor

**[Seu Nome]**

- 🔗 GitHub [@seu-usuario](https://github.com/seu-usuario)
- 💼 LinkedIn [seu-linkedin](https://linkedin.com/in/seu-linkedin)
- 📧 Email: seu.email@example.com

---

## 💬 Suporte

<div>

**Dúvidas?**
- 📝 [Abra uma Issue](https://github.com/seu-usuario/ecommerce-microservices/issues)
- 💬 [Discussões](https://github.com/seu-usuario/ecommerce-microservices/discussions)
- 📧 Email: seu.email@example.com

</div>

---

<div align="center">

## ⭐ Gostou do projeto?

Deixe uma estrela! Ajuda muito! 🙏

**[↑ Voltar ao Topo](#-ecommerce-microservices)**

</div>
