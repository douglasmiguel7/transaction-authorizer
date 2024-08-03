
# Transaction Authorizer

## Summary

- [How to use](#how-to-use)
- [Features](#features)
- [About](#about)
- [Useful links](#useful-links)
- [Contact](#contact)

## How to use

Simply clone this repository and run it using Docker Compose

Make sure Docker is installed on your machine

You can find the documentation in the link listed in the [Useful Links](#useful-links) section

## Features

You can validate a transaction (see the documentation in the [Useful Links](#useful-links) section) by sending:

```
POST /transactions/validate
{
    "accountId": "<previously created account id>",
    "totalAmount": "<transaction amount>",
    "accountId": "<previously created account id>",
    "mcc": "<mcc code>",
    "merchant": "<merchant name>"
}
```

The response status code will always be `200 OK`, but with different codes:

Approved:
```
{ "code": "00" }
```

Not enough funds:
```
{ "code": "51" }
```

Other problems:
```
{ "code": "07" }
```

## About

This repository was created for the Caju technical challenge

## Useful links

Documentation: https://documenter.getpostman.com/view/1732601/2sA3rwMDsN

## Contact

Email me: douglasandrademiguel@gmail.com

Send me a message on WhatsApp: [+55 11 936183634](https://wa.me/5511936183634)
