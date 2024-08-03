
# Transaction Authorizer

## Summary

- [How to use](#how-to-use)
- [Features](#features)
- [About](#about)
- [Useful links](#useful-links)
- [Contact](#contact)

## How to use

Just clone this repository and run it with docker compose, make sure if you have docker installed on your machine

You can see documentation on link listed in [useful links](#useful-links) section

## Features

You can validate a transaction (see documentation on [useful links](#useful-links) section) by sending:

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

Response status code always be `200 OK`, but with different codes:

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

This repository was made for Caju technical challenge

## Useful links

Documentation: https://documenter.getpostman.com/view/1732601/2sA3rwMDsN

## Contact

Email me: douglasandrademiguel@gmail.com

Send me a message on WhatsApp: [+55 11 936183634](https://wa.me/5511936183634)
