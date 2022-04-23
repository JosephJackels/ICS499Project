import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  constructor(private http: HttpClient) { 
  }

  getImage(imageUrl: string): Observable<Blob> {
	return this.http.get(imageUrl, { responseType: 'blob' });
  }
}