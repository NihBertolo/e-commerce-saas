# SaaS E-commerce Workana

![Arquitetura do projeto](img/e-commerce.drawio.png)

Este projeto é uma solução SaaS para e-commerce, permitindo que múltiplos lojistas criem e gerenciem suas lojas online de forma simples e escalável.

# Funcionalidades Planejadas e Em Andamento

## 🔑 Segurança & Identidade

- [x] OAuth com Gmail/LinkedIn/Facebook
- [ ] MFA (2FA) opcional (Google Authenticator / SMS / Email)
- [ ] Gestão de roles por tenant (admin do tenant, gestor, usuário comum)
- [ ] Rate limiting e proteção contra fraude (especialmente na API de pagamentos)
- [ ] Auditoria de ações (quem fez o quê, quando)

## 🛒 Domínios de Negócio

- [x] Cart / Catálogo / Order / Payment / User / Common
- [ ] Shipping/Delivery: integração com Correios, Loggi, Jadlog etc.
- [ ] Pricing & Discount Engine: cupons, promoções, regras de frete grátis
- [ ] Invoice/Fiscal: integração com Nota Fiscal eletrônica (NFe)
- [ ] Tenant Management: domínio próprio para criar, atualizar e suspender tenants

## 💳 Pagamentos

- [x] Estrutura multi-gateway definida
- [ ] Primeira integração real (Stripe / Pagar.me / MercadoPago)
- [ ] Webhooks de conciliação (atualizar status de pedidos com base na resposta do gateway)
- [ ] Gestão de chargebacks
- [ ] Split rules configuráveis (taxa SaaS fixa + percentual do tenant)

## 🏗️ Infraestrutura (Terraform + GitHub Actions)

**Terraform módulos:**
- [x] VPC, RDS, Redis, Security Groups
- [ ] ECS/EKS para rodar a app
- [ ] CloudFront + ACM (SSL) para frontend/API Gateway
- [ ] S3 para catálogo de imagens
- [ ] Secrets Manager / SSM para segredos dos tenants (API keys de gateways)

**GitHub Actions:**
- [ ] CI: build + testes unitários + lint + sonar
- [ ] CD: deploy automatizado em dev/staging/prod
- [ ] Segurança: dependabot, verificação de CVEs, scan de IaC

## 📊 Observabilidade

- [ ] Logs estruturados (JSON + Correlation ID por tenant)
- [ ] Métricas (Prometheus/OpenTelemetry)
- [ ] Dashboards (Grafana/CloudWatch)
- [ ] Alertas (pagamentos falhos acima de X%, latência elevada etc)

## 📈 Escalabilidade & Multi-Tenant

- [ ] Tenant resolver (middleware para identificar tenant via subdomínio, JWT ou header)
- [ ] Isolamento de dados: Shared DB com tenant_id OU schema por tenant
- [ ] Cache multi-tenant: Redis segregado por chave tenantId:resource
- [ ] Fila assíncrona (SQS/Kafka) para ordens, webhooks e notas fiscais

## 🧾 Tributação (Brasil)

- [ ] Gateway centralizado (marketplace model): SaaS emite NFe de intermediação, tenant emite NFe de venda
- [ ] Integração com SEFAZ (Nota Fiscal Eletrônica)
- [ ] Cálculo de ISS (serviço da plataforma) e ICMS/Simples Nacional (lojista)
- [x] Regras de split para separação de valores


## Tecnologias Utilizadas

- Backend: Node.js, Express
- Frontend: React.js
- Banco de Dados: PostgreSQL, Redis e Neo4J
- Autenticação: JWT
- Armazenamento de arquivos: AWS S3
- Infraestrutura: Docker, NGINX, ECS

## Arquitetura

A arquitetura do projeto segue o padrão de microsserviços, garantindo escalabilidade e facilidade de manutenção. Os principais componentes são:

- **API Gateway:** Gerencia o roteamento das requisições.
- **Serviço de Autenticação:** Responsável pelo login e registro de usuários.
- **Serviço de Produtos:** Gerencia o catálogo de produtos.
- **Serviço de Pedidos:** Processa e acompanha os pedidos.
- **Frontend Web:** Interface para lojistas e clientes.
- **Banco de Dados:** Armazena informações persistentes.

