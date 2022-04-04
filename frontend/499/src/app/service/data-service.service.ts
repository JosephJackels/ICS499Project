import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataServiceService {

  constructor(private http: HttpClient) { }

  get(){
    //return this.http.get<WidgetListResponse>('widgets').pipe(map(response => {return this.widgets;}));
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
