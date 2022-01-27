##Crypto Currency test api
###About api: 
simple api that fetch data every 30 second from CEX.IO to POSTGRES db
###What it can do:
* GET /cryptocurrencies/minprice?name=[currency_name] - return record with the lowest price of selected cryptocurrency.
* GET /cryptocurrencies/maxprice?name=[currency_name] - return record with the highest price of selected cryptocurrency.
* GET /cryptocurrencies?name=[currency_name]&page=[page_number]&size=[page_size] - return a selected page with selected number of elements and default sorting should be by price from lowest to highest. For example, if page=0&size=10, then it returns first 10 elements from database, sorted by price from lowest to highest. [page_number] and [page_size] request parameters optional, so if they are missing values page=0, size=10.
* GET /cryptocurrencies/csv Report contain the following fields: Cryptocurrency Name, Min Price, Max Price. So there only three records in that report.
###How to start api:
* clone from github and start on your intellijIdea
* add your properties in application.properties to connect postgres db

###P.S.
I understand that for this task I should use MongoDB, but I had little difficulties to install it on Windows 7, 
so if it will need, I can send task using cloud mongoDB) 