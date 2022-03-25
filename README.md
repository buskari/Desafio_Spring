## **API DOCUMENTAÇÃO - CADASTRO PRODUTOS E CLIENTES**

API elaborada para monitorar e cadastrar produtos e clientes.


**FUNCIONALIDADES:**

* Cadastrar Produtos e Clientes
* Buscar produtos e Clientes,
* Ordernar produtos,
* Filtrar produtos e clientes,
* Solicitar pedido de envio de compra



## **Parâmetros de Produto:**

**Cadastrar Produto:**

O cadastro do produto é recebido via JSON, convertido para objeto JAVA e então gravado em um arquivo products.json no diretório "./products.json"

**Endpoint** 

http://localhost:8080/api/v1/insert-products-request

**Modelo de payload**

```
{
  "products" : [
    {
      "productId": 1,
      "name": "Serra de Bancada",
      "category": "Ferramentas",
      "brand": "FORTGPRO",
      "price": 1800,
      "quantity": 5,
      "freeShipping": true,
      "prestige": "****"
    },
        
  ]
}
```

*Em caso de erro será lançado uma exceção no sistema informando que os campos não podem ser nulos*

*O sistema de cadastro não permite duplicidade de productId*




**Consultar estoque de produtos:**

A consulta dos produtos cadastrados é feito via consulta de arquivo que retorna todos os produtos cadastrados.

Para efetuar a consulta dos produtos utilizamos o metodo HTTP **GET** através do seguinte link:

**Endpoint**

http://localhost:8080/api/v1/products

**Modelo de resposta**

```
[
  {
    "productId": 1,
    "name": "Serra de Bancada",
    "quantity": 5,
  },        
]
```

*A consulta irá retornar a lista em modo crescente de productId*

*O modelo de resposta acima é o mesmo para ordenação e filtragem*




**Ordenar produtos:**

Para ordenar os produtos, utilizamos o parametro "order" com o valor de 0 a 3 definidos abaixo:
*0: retorna a lista em ordem alfabética*
*1: retorna a lista em ordem alfabética de baixo para cima*
*2: retorno a lista em ordem crescente de preço*
*3: retorna a lista em ordem descrescente de preço*

**Endpoint**

http://localhost:8080/api/v1/produtos?order={parametro}




**Filtrar produtos:**

Para filtrar os produtos, utilizamos os seguintes parâmetros **opcionais** e podem ser **combinados** entre si:
*name*
*category*
*brand*
*price*
*quantity*
*freeShipping*
*prestige*

**Endpoint**

Exemplo de aplicação de 1 filtro:

http://localhost:8080/api/v1/produtos?name={parametro}

Exemplo de combinação de 2 filtros:

http://localhost:8080/api/v1/produtos?name={nameParam}&category={categoryParam}



**Solicitar pedido de envio de compra:**

Para solicitar o pedido de envio, devemos utilizar o método **POST** com o seguinte modelo de payload no corpo da requisição:

```
{
  "purchasedProducts" : [
    {
      "productId": 1,
      "name": "Serra de Bancada",
      "brand": "FORTGPRO",
      "quantity": 5,
    }        
  ]
}
```

**Endpoint**

http://localhost:8080/api/v1/purchase-request

**Modelo de resposta**

```
{
  "ticket": {
    "id:" 484,
    "products" : [
      {
        "productId": 1,
        "name": "Serra de Bancada",
        "category": "Ferramentas",
        "brand": "FORTGPRO",
        "price": 1800,
        "quantity": 5,
        "freeShipping": true,
        "prestige": "****"
      }
    ]
  }
}
```




## **Parâmetros de Cliente:**


**Cadastrar Cliente:**

O cadastro do cliente é recebido via JSON, convertido para objeto JAVA e então gravado em um arquivo customer.json no diretório "./customer.json".
A requisição para cadastro é feita através do metodo **POST**

**Endpoint**

http://localhost:8080/api/v1/customer

**Modelo de payload**

```
{
  {
    "customerId": 1,
    "name": "Fulano",
    "state": "MG"
  }
}
```

*Em caso de erro será lançado uma exceção no sistema informando que os campos não podem ser nulos*

*O sistema de cadastro não permite duplicidade de customerId*



**Consultar a Lista de Clientes:**

A consulta dos clientes é feita via JSON
Para efetuar a consulta o metodo **GET**

**Endpoint**

http://localhost:8080/api/v1/products

**Modelo de resposta**

```
[
  {
    "customerId": 1,
    "name": "Serra de Bancada",
    "state": "MG",
  }        
]
```




**Filtrar clientes:**

Para filtrar os clientes, utilizamos o parâmetro **state** sendo este **opcional**

**Endpoint**

http://localhost:8080/api/v1/customers?state={parametro}

