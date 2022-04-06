import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { User } from './user'

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  readonly ROOT_URL = 'http://localhost:8080'
  //user! : Observable<any>;
  
  constructor(private http: HttpClient) { 
  }

  getUser(id: any): Observable<User>{ 
    return this.http.get<User>(this.ROOT_URL + '/users/one/' + id); 
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