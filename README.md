# GestioneClienti
Simulation of a CRM Software.  
Backend is based on Spring Boot Data Jpa Rest, Java17, to organize a database that uses PostgreSQL.  
There is a Command Line Runner that provides two Users, one of which is an Admin that has access to also Admin features,2 file .csv cointaning all Regions,Provinces and Cities of Italy, 21 clients (Cliente),21 addresses(Indirizzo) and 40 invoices(Fattura) for starting, all loaded on a Database.  
Almost every method in ClienteController and FatturaController, the restControllers that manage clients and invoices, supply a copy of the endpoints to make them usable either with a JSON body (with postman's calls) or with an html get form for a better ux/ui.  
Frontend is based on Bootstrap 5 and Thymeleaf.  
Using ClienteController as a user you can search clients  
-by annual turnover (fatturato annuale)  
-by part of the business name (ragione sociale),  
-by the date of the last contact with the client (data ultimo contatto)  
-by the date the client was inserted in the db (data inserimento)
otherwhise you can sort the whole list of clients  
-by business name  
-by annual turnover  
-by the province that host the registered office.  
FatturaController' endpoints are similar to ClienteController's, indeed user is allowed to look for invoices   
-by the company that sent or received the invoice  
-the status of the invoice that is settled (Saldata) or not (Non Saldata) or other status that can be created  
-by the date or the year when the invoice has been made  
-by specifying the ranage of the amount owed or paid  
Admin features includes all the user's features, plus allows you to create or delete a client or an invoice, check the details of both and even refer to a dashboard that shows the admin the number of clients that belong to one of the three ranges of annual turnover and how many clients are located in every region or province.  
In the project are also included two page error for creating a new user that has a username or password already taken, some JUnit tests of methods and endpoints and a Postman's collection which tests all the basic endpoints.  
For the application to start, remember to set a valid db in the application.properties file inside the folder "Resources".  
When a user signup he receives the role User, also email is converted by StringAttributeConverter class and password is crypted by the hashing algorithm BCrypt when saved in the db.   
