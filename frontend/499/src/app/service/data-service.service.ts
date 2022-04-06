import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { User } from './user'
import { Login } from './login'
@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  readonly ROOT_URL = 'http://localhost:8080'
  //user! : Observable<any>;
  
  constructor(private http: HttpClient) { 
  }

  getAllUsers(): Observable<User>{
    return this.http.get<User>(this.ROOT_URL + '/users/all')
  }

  getUser(id: any): Observable<User>{ 
    return this.http.get<User>(this.ROOT_URL + '/users/one/' + id); 
  }

  loginUser(user: any, pass: any): Observable<Login>{
    let options = {headers : new HttpHeaders({'Content-Type':'application/json'})};
    let body = JSON.stringify({username: user, password: pass});
    console.log(body);
    return this.http.post<Login>(
      this.ROOT_URL + '/login',
      body,
      options)
  }

  createUser(username: any, password: any): Observable<User>{
    let options = {headers : new HttpHeaders({'Content-Type':'application/json'})};
    let body = '{username: "' + username + '", password: "' + password + '"}'
    return this.http.post<User>(
      this.ROOT_URL + '/users/add',
      body,
      options
    )
  }
}

interface Widget {
  id: number;
  type: string;
  payload: string;
}

interface WidgetListResponse {
  widgets: Widget[];
}