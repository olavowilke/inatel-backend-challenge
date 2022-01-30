## Inatel challenge

### What you need

* Java 11
* Maven
* Git

### Run the project

* Clone this project

```git clone https://github.com/olavowilke/inatel-backend-challenge.git```

* Run the MySQL container

```docker container run -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=bootdb -p 3306:3306 -p 33060:33060 -d mysql:8```

* Run the stock-management api container

```docker container run -p 8080:8080 -d lucasvilela/stock-manager```

* Run the application on your IDE.

### Endpoints

```POST: localhost:8081/stock```

Creates a new StockQuote based on the json:

```
}
  "stockId": "petr3",
  "quotes":
  {
    "2019-01-01": "10",
    "2019-01-01": "20"
  }
}
```

```GET: localhost:8081/stock/{stockId}```

Retrieves a stock quote by stockId

```GET: localhost:8081/stock```

Retrieves all registered stock quotes

##TODO create a docker image for the application##

##TODO add swagger api documentation##
