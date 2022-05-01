# Widget App
We used Spring and Angular to build a web application that can display data from several sources in widgets.

## Features:
- users will be prompted to create an account
- once logged in users will be redirected to their dashboard
- user info will be stored in browser local storage
- widgets will populate dashboard
- widgets can be weather, calendar, stock or comic type
- *duplicate widgets are not allowed*
- *duplicate usernames are not allowed*
- for weather and forecast the country of the city can be specified with the format "city, country code"
- widgets have a payload that stores data
- backend fetches data from api's and stores in payload
- backend can manage how often widgets are refreshed to prevent excess api calls
- backend authenticates users and provides access tokens
- frontend renders widgets visually
- frontend manages tokens and passes instructions to backend
- new widget types can be build in easily  
- each widget has its own template
- widgets can be removed by clicking the gear icon followed by the trash icon
- calendar uses system data

## To run:
- install spring boot
- install mysql
- create database MySQL db on port 3306 with username: testUser and password: password and account 
```
mysql> CREATE DATABASE dashboard_app_db;
mysql> USE dashboard_app_db;
mysql> -h localhost -u testuUser -ppassword
```
- install angular
```
npm install -g @angular/cli
``` 
- may require installation of additional angular modules
- run backend as a Spring Boot Project
- run frontend through npm
```
ng serve --open
``` 
- run both projects simultaneously
- **api keys with environment variables matching the code are required**

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
