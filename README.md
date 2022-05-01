# Widget App
We used Spring and Angular to build a web application that can display data from several sources in widgets.

## Features:
- users can create accounts to manage their dashboards
- widgets will populate dashboard
- widgets can be weather, calendar, stock or comic type
- widgets have a payload that stores data
- backend fetches data from api's and stores in payload
- backend can manage how often widgets are refreshed to prevent excess api calls
- backend authenticates users and provides access tokens
- frontend renders widgets visually
- frontend manages tokens and passes instructions to backend
- new widget types can be build in easily  

## To run:
- install spring boot
- install mysql
- create database MySQL db on port 3306 with username: testUser and password: password and account 
- install angular
- may require installation of additional angular modules
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
- [Open Weather](https://openweathermap.org/) (requires key)
- [Yahoo Finance](https://www.yahoofinanceapi.com/) (requires key)
- [XKCD](https://xkcd.com/json.html) (no key necessary)
    
## Authors:
- Gilbert
- Joe
- Alex
- Leo
