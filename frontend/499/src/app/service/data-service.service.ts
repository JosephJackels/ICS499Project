import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { User } from './user'
import { Login } from './login'
import { Widget } from './widget'
import { Payload } from './payload'
import { Dashboard } from './dashboard'

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  readonly ROOT_URL = 'http://localhost:8080'
  
  constructor(private http: HttpClient) { 
  }

  //user services
  getAllUsers(): Observable<User>{
    return this.http.get<User>(this.ROOT_URL + '/users/all')
  }

  getUser(token: string, id: any): Observable<User>{
    let options = {headers : new HttpHeaders({'Content-Type':'application/json', 'Authorization':token})};
    return this.http.get<User>(
      this.ROOT_URL + '/users/one/' + id, 
      options); 
  }

  loginUser(user: any, pass: any): Observable<Login>{
    let options = {headers : new HttpHeaders({'Content-Type':'application/json'})};
    let body = JSON.stringify({username: user, password: pass});
    console.log(body);
    return this.http.post<Login>(
      this.ROOT_URL + '/login',
      body,
      options);
  }

  createUser(user: any, pass: any): Observable<User>{
    let options = {headers : new HttpHeaders({'Content-Type':'application/json'})};
    let body = JSON.stringify({username: user, password: pass});
    return this.http.post<User>(
      this.ROOT_URL + '/users/add',
      body,
      options
    );
  }
  getDashboardForUser(token: string, userId: any): Observable<Dashboard>{
    let options = {headers : new HttpHeaders({'Content-Type':'application/json', 'Authorization':token})};
    return this.http.get<Dashboard>(
      this.ROOT_URL + '/users/one/' + userId + '/dashboard',
      options
    );
  }

  //widget services
  createWeatherWidget(token: string, query: string): Observable<Widget>{
    let options = {headers : new HttpHeaders({'Content-Type':'application/json', 'Authorization':token}), params : new HttpParams().set('query', query)};
    return this.http.post<Widget>(
      this.ROOT_URL + '/widgets/weather/add',
      options
    );
  }

  updateWidgetQuery(token: string, newQuery: string, widgetId: any): Observable<Widget>{
    let options = {headers : new HttpHeaders({'Content-Type':'application/json', 'Authorization':token}), params : new HttpParams().set('query', newQuery)};
    return this.http.post<Widget>(
      this.ROOT_URL + '/widgets/weather/one/' + widgetId + '/update',
      options
    );
  }

  //updates and gets current weather for given weather widget id
  getCurrentWeather(token: string, widgetId: any): Observable<Payload>{
    let options = {headers : new HttpHeaders({'Content-Type':'application/json', 'Authorization':token})};
    return this.http.get<Payload>(
      this.ROOT_URL + '/widgets/weather/one/' + widgetId + '/current',
      options
    );
  }

  //updates and gets forecast weather for given weather widget id
  getForecastWeather(token: string, widgetId: any): Observable<Payload>{
    let options = {headers : new HttpHeaders({'Content-Type':'application/json', 'Authorization':token})};
    return this.http.get<Payload>(
      this.ROOT_URL + '/widgets/weather/one/' + widgetId + '/forecast',
      options
    );
  }

  //dashboard services
  getDashboardById(token: string, dashboardId: any): Observable<Dashboard>{
    let options = {headers : new HttpHeaders({'Content-Type':'application/json', 'Authorization':token})};
    return this.http.get<Dashboard>(
      this.ROOT_URL + '/dashboards/one/' + dashboardId,
      options
    );
  }

  addWidgetToDashboard(token: string, dashboardId: any, widgetId: any): Observable<Dashboard>{
    let options = {headers : new HttpHeaders({'Content-Type':'application/json', 'Authorization':token}), params : new HttpParams().set('widgetId', widgetId)};
    return this.http.post<Dashboard>(
      this.ROOT_URL + '/dashboards/add/' + dashboardId,
      options
    );
  }

  removeWidgetFromDashboard(token: string, dashboardId: any, widgetId: any): Observable<Widget>{
    let options = {headers : new HttpHeaders({'Content-Type':'application/json', 'Authorization':token}), params : new HttpParams().set('widgetId', widgetId)};
    return this.http.post<Widget>(
      this.ROOT_URL + '/dashboards/remove/' + dashboardId,
      options
    );
  }
}