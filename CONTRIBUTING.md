# 🤝 Contribuindo para Payment API

Obrigado por considerar contribuir para este projeto! 🎉

## 📋 Como Contribuir

### 1️⃣ Fork o Projeto

```bash
# Clone seu fork
git clone https://github.com/seu-usuario/payment-api.git
cd payment-api
```

### 2️⃣ Crie uma Branch

```bash
git checkout -b feature/MinhaNovaFeature
```

**Convenção de nomes:**
- `feature/` - Nova funcionalidade
- `bugfix/` - Correção de bug
- `docs/` - Mudanças na documentação
- `refactor/` - Refatoração de código
- `test/` - Adição ou correção de testes

### 3️⃣ Faça suas Alterações

- Siga o padrão de código do projeto
- Adicione testes se aplicável
- Mantenha commits pequenos e descritivos

### 4️⃣ Commit suas Mudanças

```bash
git add .
git commit -m "feat: Adiciona validação de CPF nos pagamentos"
```

**Convenção de commits (Conventional Commits):**
- `feat:` - Nova funcionalidade
- `fix:` - Correção de bug
- `docs:` - Mudanças na documentação
- `style:` - Formatação (sem mudança de lógica)
- `refactor:` - Refatoração de código
- `test:` - Adição ou correção de testes
- `chore:` - Tarefas de manutenção

### 5️⃣ Push para o GitHub

```bash
git push origin feature/MinhaNovaFeature
```

### 6️⃣ Abra um Pull Request

1. Vá para o repositório no GitHub
2. Clique em "Compare & pull request"
3. Descreva suas mudanças detalhadamente
4. Aguarde o review

## ✅ Checklist antes do PR

- [ ] Código compila sem erros
- [ ] Testes passam (`./mvnw test`)
- [ ] Documentação atualizada (README, Swagger)
- [ ] Commits seguem a convenção
- [ ] Branch atualizada com `main`

## 🧪 Executando Testes

```bash
# Rodar todos os testes
./mvnw test

# Rodar com cobertura
./mvnw test jacoco:report
```

## 📝 Padrões de Código

### Java
- Usar Java 21+
- Seguir convenções do Google Java Style Guide
- Usar Lombok para reduzir boilerplate
- Comentários em inglês ou português (consistente)

### Estrutura de Pacotes
```
com.goeasy.teste/
├── config/       # Configurações
├── controller/   # Endpoints REST
├── dto/          # Data Transfer Objects
├── exception/    # Exceções customizadas
├── model/        # Entidades JPA
├── repository/   # Acesso a dados
└── service/      # Lógica de negócio
```

### Nomenclatura
- Classes: `PascalCase` (ex: `PaymentService`)
- Métodos: `camelCase` (ex: `createPayment`)
- Constantes: `UPPER_SNAKE_CASE` (ex: `MAX_AMOUNT`)
- Packages: `lowercase` (ex: `com.goeasy.teste`)

## 🐛 Reportando Bugs

Abra uma issue com:

- **Descrição clara** do problema
- **Passos para reproduzir**
- **Comportamento esperado** vs **comportamento atual**
- **Screenshots** (se aplicável)
- **Versão** do Java, Spring Boot, etc.

## 💡 Sugerindo Features

Abra uma issue com:

- **Descrição da feature**
- **Por que seria útil**
- **Possível implementação** (se tiver ideias)

## 📞 Precisa de Ajuda?

- Abra uma issue com a tag `question`
- Entre em contato via email: seu.email@example.com

## 🎯 Áreas que Precisam de Contribuição

- [ ] Testes unitários e de integração
- [ ] Implementação de webhooks Stripe
- [ ] Autenticação/Autorização (Spring Security)
- [ ] Dockerização
- [ ] CI/CD (GitHub Actions)
- [ ] Migrações de banco (Flyway)
- [ ] Cache com Redis
- [ ] Internacionalização (i18n)

---

**Obrigado por contribuir! 🚀**
