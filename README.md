# CostControl
#### How to run application locally
1. Clone repository
2. First, you need to connect your database.To do this, enter your values in the application.properties file.
   You need to create a file named application.properties that should be located at the following path: src/main/resources/application.properties. Mark the resources folder as the resources root by right-clicking on the folder, selecting "Mark Directory As" -> "Resources root."
```
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
spring.jpa.properties.hibernate.dialect=
```
3. Create a file named .env where environment variables necessary for running PostgreSQL in Docker should be located.
```
USERNAME=
PASSWORD=
DB=
```
4. Run Docker Engine
4. Open a command prompt and navigate to the directory
5. Use to run application
``` 
docker-compose up --build
```
6. Use to stop application
``` 
docker-compose down
```
7. After starting the application, it can be accessed locally at `http://localhost:8080`
8. Personal task 
Group : IO-15
15%3=0
Variant(0) : 
"Облік доходів - потрібно зробити сутність “рахунок” куди можна додавати гроші по мірі їх надходження(для кожного користувача свій) і звідти списуються кошти атоматично при створенні нової витрати. Логіка щодо заходу в мінус лишається на розсуд студентів(можете або дозволити це, або заборонити).
   "

Deploy link: https://costcontrol.onrender.com