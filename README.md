# 📘 README — Instruções de Execução

## ▶️ Build do Ambiente
Para construir o ambiente, execute o comando abaixo:
```bash
docker-compose up --build
```

## 🔐 Gerar Token de Desenvolvimento
Se você implementou o endpoint:

**POST** `/auth/token`

Use-o para gerar o token JWT. Caso contrário, utilize o utilitário `JwtUtil` localmente para gerar um token válido.

## 🚀 Acionar Carga Inicial de Marcas (API-1)
Para acionar a carga inicial de marcas, faça uma requisição:

**POST** `http://localhost:8080/v1/load/brands`

### Headers:
- **Authorization**: `Bearer <token>`

## 📡 Verificar Consumo pela API-2
Acompanhe os logs para confirmar que:
- A API-2 está consumindo o tópico Kafka `marcas`.
- A API-2 está salvando os resultados no banco de dados Postgres.

## 🔍 Consultar Veículos por Marca (Cache Redis)
Para consultar veículos por marca, utilize a rota abaixo:

**GET** `http://localhost:8080/v1/vehicles?brandCode=<code>`

Essa rota utiliza cache via Redis.

## ✏️ Atualizar Informações de um Veículo
Para atualizar as informações de um veículo, faça uma requisição:

**PUT** `/v1/vehicles/{id}`

### Body (JSON):
```json
{
  "model": "novo",
  "observacoes": "x"
}
```