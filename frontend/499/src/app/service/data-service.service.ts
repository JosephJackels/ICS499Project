import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  constructor(private http: HttpClient) { 
    this.getData();
  }
  user!: User;
  loading: boolean = false;
  errorMessage: string | undefined;

  getServices():Observable<any> { 
    return this.http.get('http://localhost:8080/users/one/5'); 
  }

  public getData(){
    this.loading = true;
    this.errorMessage = "";
    this.http.get('http://localhost:8080/users/one/5').subscribe(
      (response) => {                           //next() callback
        console.log('response received')
        this.user = response as User; 
        this.loading = false;
      },
      (error) => {                              //error() callback
        console.error('Request failed with error')
        this.errorMessage = error;
        this.loading = false;
      },
      () => {                                   //complete() callback
        console.error('Request completed')      //This is actually not needed 
        this.loading = false; 
      }
    )
  }

  public getUser(){
    console.log(this.user);
    return this.user;
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

export interface User{
  userID: number;
  username: string;
  password: string;
}