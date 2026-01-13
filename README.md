# 💳 Payment API - Spring Boot + Stripe Integration

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.1-brightgreen)
![Stripe](https://img.shields.io/badge/Stripe-22.24.0-blueviolet)
![License](https://img.shields.io/badge/license-MIT-blue)

> **API REST profissional para gerenciamento de pagamentos com integração Stripe, desenvolvida com Spring Boot 4 seguindo as melhores práticas de arquitetura em camadas.**

---

## 🚀 Sobre o Projeto

Este projeto demonstra a implementação de um sistema completo de pagamentos utilizando:

- **Spring Boot 4.0.1** - Framework Java mais moderno
- **Stripe API** - Integração com gateway de pagamento internacional
- **Arquitetura em Camadas** - Controller → Service → Repository
- **Bean Validation** - Validações robustas de entrada
- **JPA/Hibernate** - Persistência com H2 Database
- **Swagger/OpenAPI** - Documentação interativa da API
- **Spring Actuator** - Health checks e métricas
- **Exception Handling Global** - Tratamento padronizado de erros

---

## 🎯 Funcionalidades

- ✅ Criar pagamentos via Stripe Payment Intent
- ✅ Consultar pagamentos por ID
- ✅ Listar todos os pagamentos (com filtro opcional por email)
- ✅ Validação automática de dados (Bean Validation)
- ✅ Tratamento global de exceções customizadas
- ✅ Documentação interativa (Swagger UI)
- ✅ Health check e métricas (Actuator)
- ✅ Console H2 para visualização do banco

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| Java | 21 | Linguagem de programação |
| Spring Boot | 4.0.1 | Framework principal |
| Spring Data JPA | 4.0.1 | Persistência de dados |
| Spring Validation | 4.0.1 | Validações Bean Validation |
| Spring Actuator | 4.0.1 | Monitoramento e métricas |
| H2 Database | Runtime | Banco de dados em memória |
| Stripe Java SDK | 22.24.0 | Integração com Stripe |
| Springdoc OpenAPI | 2.7.0 | Documentação Swagger |
| Lombok | Latest | Redução de boilerplate |
| Maven | 3.9+ | Gerenciador de dependências |

---

## 📁 Arquitetura do Projeto

```
src/main/java/com/goeasy/teste/
├── config/
│   ├── StripeConfig.java          # Configuração do SDK Stripe
│   └── H2ConsoleConfig.java       # Habilitação do console H2
├── controller/
│   └── PaymentController.java     # Endpoints REST
├── dto/
│   ├── PaymentRequest.java        # DTO de entrada com validações
│   └── PaymentResponse.java       # DTO de resposta
├── exception/
│   ├── PaymentNotFoundException.java   # Exceção customizada
│   └── GlobalExceptionHandler.java     # Tratamento global de erros
├── model/
│   ├── Payment.java               # Entidade JPA
│   └── PaymentStatus.java         # Enum de status
├── repository/
│   └── PaymentRepository.java     # Acesso ao banco de dados
├── service/
│   └── PaymentService.java        # Lógica de negócio
└── TesteApplication.java          # Classe principal
```

---

## ⚙️ Pré-requisitos

- **Java 21** ou superior
- **Maven 3.9+**
- **Chave de API Stripe** (modo teste)
- **IDE** (IntelliJ IDEA, Eclipse, VS Code)

---

## 🚀 Como Executar

### 1️⃣ Clone o repositório

```bash
git clone https://github.com/seu-usuario/payment-api.git
cd payment-api
```

### 2️⃣ Configure a chave Stripe

Edite `src/main/resources/application.properties`:

```properties
stripe.api.key=sk_test_SUA_CHAVE_AQUI
```

> ⚠️ **Importante:** Use apenas chaves de teste (`sk_test_...`). NUNCA commite chaves reais!

### 3️⃣ Execute a aplicação

```bash
./mvnw spring-boot:run
```

Ou via IDE: Execute a classe `TesteApplication.java`

### 4️⃣ Acesse os endpoints

- **API Base:** http://localhost:8080/api/payments
- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **H2 Console:** http://localhost:8080/h2-console
- **Health Check:** http://localhost:8080/actuator/health

---

## 📚 Documentação da API

### **Criar Pagamento**

```http
POST /api/payments
Content-Type: application/json

{
  "amount": 150.00,
  "currency": "USD",
  "customerEmail": "customer@example.com",
  "description": "Order #12345"
}
```

**Resposta (201 Created):**
```json
{
  "id": 1,
  "amount": 150.00,
  "currency": "USD",
  "customerEmail": "customer@example.com",
  "description": "Order #12345",
  "paymentIntentId": "pi_3QgT...",
  "clientSecret": "pi_3QgT..._secret_...",
  "status": "PENDING",
  "createdAt": "2026-01-13T14:30:00"
}
```

---

### **Buscar Pagamento por ID**

```http
GET /api/payments/{id}
```

**Resposta (200 OK):**
```json
{
  "id": 1,
  "amount": 150.00,
  "currency": "USD",
  "customerEmail": "customer@example.com",
  "description": "Order #12345",
  "paymentIntentId": "pi_3QgT...",
  "clientSecret": null,
  "status": "PENDING",
  "createdAt": "2026-01-13T14:30:00"
}
```

---

### **Listar Pagamentos**

```http
GET /api/payments?customerEmail=customer@example.com
```

**Resposta (200 OK):**
```json
[
  {
    "id": 1,
    "amount": 150.00,
    "currency": "USD",
    "customerEmail": "customer@example.com",
    "paymentIntentId": "pi_3QgT...",
    "status": "PENDING",
    "createdAt": "2026-01-13T14:30:00"
  }
]
```

---

### **Tratamento de Erros**

**Validação (400 Bad Request):**
```json
{
  "timestamp": "2026-01-13T14:32:00",
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "amount": "Amount must be greater than 0",
    "customerEmail": "Invalid email format"
  }
}
```

**Pagamento não encontrado (404 Not Found):**
```json
{
  "timestamp": "2026-01-13T14:33:00",
  "status": 404,
  "message": "Payment not found with ID: 999",
  "errors": null
}
```

---

## 🧪 Testando a API

### **Via Swagger UI (Recomendado)**

1. Acesse: http://localhost:8080/swagger-ui.html
2. Explore os endpoints disponíveis
3. Clique em "Try it out" para testar
4. Veja os schemas e validações

### **Via cURL**

```bash
# Criar pagamento
curl -X POST http://localhost:8080/api/payments \
  -H "Content-Type: application/json" \
  -d '{
    "amount": 99.99,
    "currency": "USD",
    "customerEmail": "test@example.com",
    "description": "Test payment"
  }'

# Buscar pagamento
curl http://localhost:8080/api/payments/1

# Listar pagamentos
curl http://localhost:8080/api/payments

# Health check
curl http://localhost:8080/actuator/health
```

### **Via Postman**

Importe a collection disponível em: `docs/postman_collection.json`

---

## 🔒 Segurança

### **Implementado:**
- ✅ Validação de entrada com Bean Validation
- ✅ Tratamento global de exceções
- ✅ clientSecret retornado apenas na criação
- ✅ Chave Stripe em arquivo de configuração (não hardcoded)

### **Próximos passos (Produção):**
- 🔲 Autenticação OAuth2/JWT
- 🔲 HTTPS obrigatório
- 🔲 Rate Limiting
- 🔲 Secrets em variáveis de ambiente/AWS Secrets Manager
- 🔲 CORS configurado para domínios específicos
- 🔲 Implementar webhooks Stripe para atualização de status

---

## 📊 H2 Console

Para visualizar os dados persistidos:

1. Acesse: http://localhost:8080/h2-console
2. Configure:
   - **JDBC URL:** `jdbc:h2:mem:testdb`
   - **User:** `sa`
   - **Password:** (deixe em branco)
3. Execute queries:
   ```sql
   SELECT * FROM payments;
   ```

---

## 🏗️ Padrões e Boas Práticas

Este projeto implementa:

- ✅ **Clean Architecture** - Separação clara de responsabilidades
- ✅ **DTOs** - Desacoplamento entre API e entidades
- ✅ **Repository Pattern** - Abstração de acesso a dados
- ✅ **Service Layer** - Lógica de negócio centralizada
- ✅ **Exception Handling Global** - Respostas de erro padronizadas
- ✅ **Builder Pattern** - Construção de objetos complexos (Lombok)
- ✅ **Dependency Injection** - Inversão de controle (Spring)
- ✅ **OpenAPI Specification** - Documentação como código
- ✅ **Health Checks** - Observabilidade básica

---

## 🚀 Melhorias Futuras

- [ ] Implementar testes unitários (JUnit + Mockito)
- [ ] Adicionar testes de integração (@SpringBootTest)
- [ ] Configurar CI/CD (GitHub Actions)
- [ ] Implementar webhooks Stripe para atualização de status
- [ ] Adicionar autenticação/autorização (Spring Security)
- [ ] Migrar para PostgreSQL em produção
- [ ] Implementar cache com Redis
- [ ] Adicionar logging estruturado (ELK Stack)
- [ ] Containerização com Docker
- [ ] Deploy em AWS/Azure/Heroku

---

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para:

1. Fazer fork do projeto
2. Criar uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abrir um Pull Request

---

## ⭐ Mostre seu apoio

Se este projeto te ajudou, deixe uma ⭐!

---

## 📸 Screenshots

### Swagger UI
![Swagger UI](docs/screenshots/swagger-ui.png)

### H2 Console
![H2 Console](docs/screenshots/h2-console.png)

### Health Check
![Health Check](docs/screenshots/health-check.png)

---

## 📚 Referências

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Stripe API Reference](https://stripe.com/docs/api)
- [Swagger/OpenAPI Specification](https://swagger.io/specification/)
- [Bean Validation Specification](https://beanvalidation.org/)

---

**Desenvolvido com ❤️ e Spring Boot**
