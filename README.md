# boardcamp_spring

API to control rents and devolutions for a boardgame store.

# Routes

## /games
### GET `/games`
- Return `200 (OK)` along with a list of objects in this format:
```
[
  {
    id: 1,
    name: 'Monopoly',
    image: 'http://',
    stockTotal: 3,
    pricePerDay: 1500
  },
  {
    id: 2,
    name: 'Clue',
    image: 'http://',
    stockTotal: 1,
    pricePerDay: 2500
  },
]
```

### POST `/games`
- Request:
```
{
  name: 'Monopoly',
  image: 'http://www.image.com./monopoly.jpg',
  stockTotal: 3,
  pricePerDay: 1500
}
```
- Return `201 (CREATED)` along with an object in this format:
```
{
	id: 1,
  name: 'Monopoly',
  image: 'http://www.image.com./monopoly.jpg',
  stockTotal: 3,
  pricePerDay: 1500
}
```

## /customers
This model uses CPF (_Cadastro de Pessoa Física_, Individual Taxpayer Registration), a Brazilian document to validate customers.

### GET `/customers/:id`
- Return `200 (OK)` along with an object in this format:
```
{
  id: 1,
  name: 'John Doe',
  cpf: '01234567890'
}
```

### POST `/customers/`
- Request:
```
{
  name: 'John Doe',
  cpf: '01234567890'
}
```
- Return `201 (CREATED)` along with an object in this format:
```
{
	id: 1,
  name: 'John Doe',
  cpf: '01234567890'
}
```

## /rentals
### GET `/rentals`
- Return `200 (OK)` along with a list of objects in this format:
```
[
  {
    id: 1,
    rentDate: '2021-06-20',
    daysRented: 3,
    returnDate: null, /changes when returned
    originalPrice: 4500,
    delayFee: 0, //changes to a value if returned late
    customer: {
      id: 1,
      name: 'John Doe',
		  cpf: '01234567890'
    },
    game: {
      id: 1,
      name: 'Monopoly',
      image: 'http://www.image.com./monopoly.jpg',
      stockTotal: 3,
      pricePerDay: 1500
    }
  }
]
```
Each object have the the customer who rented along with the game they rented.


### POST `/rentals/`
- Request:
```
{
  customerId: 1,
  gameId: 1,
  daysRented: 3
}
```
- Return `201 (CREATED)` along with an object in this format:
```
{
    id: 1,
    rentDate: '2021-06-20',
    daysRented: 3,
    returnDate: null, 
    originalPrice: 4500,
    delayFee: 0, 
    customer: {
      id: 1,
      name: 'John Doe',
		  cpf: '01234567890'
    },
    game: {
      id: 1,
      name: 'Monopoly',
      image: 'http://www.image.com./monopoly.jpg',
      stockTotal: 3,
      pricePerDay: 1500
    }
  }
```

### PUT `/rentals/:id/return`
- Change `returnDate` to the date the request is made.
- If there's a fee, it will also put a value in `delayFee`.
- Return `200 (OK)` along with an object in this format:
```
{
    id: 1,
    rentDate: '2021-06-20',
    daysRented: 3,
    returnDate: '2021-06-25', 
    originalPrice: 4500,
    delayFee: 3000, 
    customer: {
      id: 1,
      name: 'John Doe',
		  cpf: '01234567890'
    },
    game: {
      id: 1,
      name: 'Monopoly',
      image: 'http://www.image.com./monopoly.jpg',
      stockTotal: 3,
      pricePerDay: 1500
    }
  }
```