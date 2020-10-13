# Payment process API instructions
## Prerequisites for paypal sanbox account and access token.
    1. Create sanbox account on paypal developer.
	2. Follow the instruction provided there and generate access token.
	3. Provide token value for property payment.transaction.access.tocken in application.properties file
## Prerequisites
	1. Java 8 installed.
	2. Should have eclipse IDE.
	3. Should have maven or eclipse maven plugin.
	4. Should have mysql database installed in local machin.
	5. Should have Postman tool

## To build the application use following instruction.
	1. Import as maven project in eclipse. 
	2. Create airportdb schema in mysql database.
    2. After import right click on project and go to run and click on Maven Install.
    3. After build right click on project and go to run and click on Spring Boot App or run as java application.
    4. Execute provided sql script along with project.

## To test payment process api's use postman

   1. API URL : http://localhost:8080/api/payment/payment-info
   2. Adding json request in project root directory with file name paymentRequest.txt
	
