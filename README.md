
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

```json
POST /transactions/validate
{
    "accountId": "<previously created account id>",
    "totalAmount": "<transaction amount>",
    "mcc": "<mcc code>",
    "merchant": "<merchant name>"
}
```

The response status code will always be `200 OK`, but with different codes:

Approved:
```json
{ "code": "00" }
```

Not enough funds:
```json
{ "code": "51" }
```

Other problems:
```json
{ "code": "07" }
```

## Avoiding duplicate transactions

We can implement the capability to prevent transaction duplication in a future feature

My first idea is to use Redis to create a unique key that indicates if an account is blocked for new transactions

In this scenario, we can do two things:
1. New transactions wait for the account to be unlocked
2. New transactions are immediately rejected with code 07

Below, you can see a simple diagram to illustrate the idea:

If you would like, you can view this diagram on Excalidraw: https://excalidraw.com/#json=Ghp-x1wkcCUIkPlJTHAP0,cZcDdm3P6iZaT_4J64pfyw

![image](https://github.com/user-attachments/assets/5f003678-20d0-404a-a8fc-966a7bd591fa)

## About

This repository was created for the Caju technical challenge

## Useful links

Documentation: https://documenter.getpostman.com/view/1732601/2sA3rwMDsN

## Contact

Email me: douglasandrademiguel@gmail.com

Send me a message on WhatsApp: [+55 11 936183634](https://wa.me/5511936183634)
