# GestioneClienti  
## English    
Simulation of a CRM Software.  
Backend is based on Spring Boot Data Jpa Rest,using Java17, to organize a database that uses PostgreSQL.  
There is a Command Line Runner that provides:
-two Users, one of which is an Admin that also has access to Admin features,  
-2 file .csv cointaning all Regions,Provinces and Cities of Italy,   
-21 clients (Cliente),  
-21 addresses(Indirizzo),   
-40 invoices(Fattura) 
for starters, these are all loaded on a Database.  
Almost every method in ClienteController and FatturaController, the restControllers that manage clients and invoices, supply a copy of the endpoints to make them usable either with a JSON body (with postman's calls) or with a html get form for a better ux/ui.  
Frontend is based on Bootstrap 5 and Thymeleaf.  
Using ClienteController as a user you can search clients by:  
-annual turnover (fatturato annuale),  
-part of the business name (ragione sociale),  
-the date of the last contact with the client (data ultimo contatto),  
-the date the client was inserted in the db (data inserimento),
otherwhise you can sort the whole list of clients by:   
-business name,  
-annual turnover,  
-the province that hosts the registered office.  
FatturaController's endpoints are similar to ClienteController's, indeed user is allowed to look for invoices by:   
-the company that sent or received the invoice,  
-the status of the invoice that is settled (Saldata) or not (Non Saldata) or other status that can be created,  
-the date or the year when the invoice has been made,  
-specifying the range of the amount owed or paid,  
Admin features includes all user's features, plus they allows you to:  
-create or delete a client or an invoice,  
-check the details of both,  
-refer to a dashboard that shows the admin the number of clients that belong to one of three ranges of annual turnover and how many clients are located in every region and province.  
Two html error pages are also included in the project that appear when creating a new user that has a username or email already taken.   
Furthermore, it includes some JUnit tests of methods and endpoints and a Postman's collection which tests all the basic endpoints.  
For the application to start, remember to set a valid db in the application.properties file inside the folder "Resources".  
When a user signs up he receives the role User.   
His email is converted by StringAttributeConverter class and his password is crypted by the hashing algorithm BCrypt before saving in the db.  
## Italiano    
Simulazione di un software CRM di gestione clienti.  
La parte riguardante il backend è basata su Spring Boot Data Jpa Rest, utilizzando Java 17, ai fini di organizzare un database tramite PostrgreSQL.  
C'è una classe CommandLineRunner che fornisce:  
-Due Utenti, uno dei quali è un Admin che ha anche accesso a metodi esclusivi,  
-2 file .csv che contengono tutte le Regioni, Province e Comuni italiani,  
-21 clienti,
-21 indirizzi,  
-40 fatture
per cominciare, tutto caricato sul Database.
Pressochè ogni metodo in ClienteController e FatturaController, i restController che gestiscono clienti e fatture, presentano una copia degli endpoint affinchè siano utilizzabili sia con un "JSON body" (Per chiamate da Postman) sia con un get form html da un'interfaccia utente.  
La parte Frontend si basa su Bootstrap 5 e Thymeleaf.   
Utilizzando ClienteController come utente è possibile cercare clienti per:  
-fatturato annuale,  
-parte della ragione sociale del cliente,  
-data di ultimo contatto col cliente,
-data in cui il cliente è stato inserito nel db,  
Oppure è possibile ordinare l'intera lista di clienti per:  
-ragione sociale,  
-fatturato annuale,  
-provincia dell'indirizzo della sede legale  
Gli endpoint di FatturaController sono simili a quelli di ClienteController, infatti l'utente può cercare le fatture per:  
-Cliente,
-Stato della fattura, che può essere SALDATA o NON SALDATA, oppure ne può essere creato uno nuovo,  
-Data o anno della fattura,  
-Specificando un range di importo delle fatture
Un utente admin ha accesso a tutte le funzionalità degli user, in più può:  
-creare o eliminare un cliente o fattura,  
-controllare i dettagli di entrambi,  
-consultare una dashboard che mostra all'admin il numero di clienti che appartengono a uno di tre range di fatturato annuale e quanti clienti sono situati in ogni Regione e Provincia
Inoltre sono incluse nel progetto due pagine html di errore che compaiono quando viene creato un nuovo utente che ha username o email già utilizzati.  
In più, il progetto include alcuni test JUnit sui metodi e endpoint, e una collezione Postman che testa tutti gli endpoint di base.
Per avviare l'applicazione, è importante ricordarsi di indicare un database valido nel file "application.properties" all'interno della cartella "Resources".  
Quanto un utente si registra riceve il ruolo di user, l'email viene convertita tramite una classe "StringAttributeConverter" mentre la password è criptata dall' algoritmo di hashing BCrypt, prima di essere registrati nel database.
