# Widget App
We used Spring and Angular to build a web application that can display data from several sources in widgets.

## Features:
- users can create accounts to manage their dashboards
- widgets will populate dashboard
- widgets can be weather, calendar, stock or comic type
- backend fetches data from api's
- backend can manage how often widgets are refreshed to prevent excess api calls
- backend authenticates users and provides access tokens
- frontend renders widgets visually
- frontend manages tokens and passes instructions to backend

## To run:
- install spring boot
- install mysql
- create database with name and account 
- install angular
- run backend as a Spring Boot Project
- run frontend through npm
```
npm serve
``` 
- run both projects simultaneously
- api keys with environment variables matching the code are **required**

### Take a look:
![Demo!](/frontend/499/src/assets/img/demo.png "demo")

## API's we used:
- [Open Weather](https://openweathermap.org/)
- [Yahoo Finance](https://www.yahoofinanceapi.com/) 
    
## Authors:
- Gilbert
- Joe
- Alex
- Leo
