# Getting Started
#
## Prerequisites
* java 8
* postgres 12
#
## Run locally
* create database in postgres with name deliveryfood
* change connection properties in application.properties
#
## In browser
* http://localhost:8080/swagger-ui.html
#
## In use
### my user
* create ADMIN
* other
### restaurant
* create RESTAURANT
* other
### client
* create CLIENT
* other
### user display
* display who is logged
### product
* the restaurant can add the products
* other
### product in cart
* the client can select the products from one restaurant
* other
### cart
* the client add the products in the cart (POST - without any changes)
* the client can send command
* other
### pending cart
* the restaurant can accept the command
* other
### in progress
* display command in progress
* confirm delivered command
