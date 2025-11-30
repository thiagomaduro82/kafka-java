# 📘 README — Instruções de Execução

## ▶️ Build do Ambiente
Para construir o ambiente, execute o comando abaixo:

```bash
docker-compose up --build
```

## 🔐 Gerar Token de Acesso aos endpoints
Use esses dados de username e password para gerar o token JWT.

**POST** `/auth/login`

```bash
{
  "username": "user",
  "password": "password"
}
```

## ATENÇÃO!
Para todas as requisições é necessário passar o cabeçalho abaixo:

### Headers:
- **Authorization**: `Bearer <token>`


## 🚀 Acionar Carga Inicial de Marcas (API-1)
Para acionar a carga inicial de marcas, faça uma requisição:

**POST** `http://localhost:8080/load-initial-data`

## 📡 Verificar Consumo pela API-2
Acompanhe os logs para confirmar que:
- A API-2 está consumindo o tópico Kafka `brands-topic`.
- A API-2 está salvando os resultados no banco de dados Postgres.

## 🔍 Consultar todas as Marcas (Cache Redis)
Para consultar todas as marcas, utilize a rota abaixo:

**GET** `http://localhost:8080/brands`

## 🔍 Consultar todos os Veículos (Cache Redis)
Para consultar todos os veículos, utilize a rota abaixo:

**GET** `http://localhost:8080/vehicles`

## 🔍 Consultar todos os Veículos por Marca (Cache Redis)
Para consultar todos os veículos por marca, utilize a rota abaixo:

**GET** `http://localhost:8080/vehicles/by-brand/{brandId}`

## 🔍 Consultar Alterar dados do veículo
Para alterar o modelo e adicionar uma observação, utilize a rota abaixo:

**PUT** `http://localhost:8080/vehicles/update/{vehicleId}`
Deve ser passado um objeto VehicleDTO no corpo da requisição em formato JSON.
```bash
{
  "modelName": "Model name updated",
  "observation": "Anything"
}
```

## Todos os endpoints podem ser acessados via Swagger
Para acessar os endpoints via Swagger, utilize a seguinte url:

**GET** `/swagger-ui/index.html`

## O PGADMIN está disponível
Caso queira verificar os dados diretamente na base dados, você pode acessar:

**GET** `http://localhost:8082`

Informar as credenciais:

Username = admin@admin.com
Password = admin

Isso permitirá você verificar as tabelas criadas bem como os dados dentro do postgres.
