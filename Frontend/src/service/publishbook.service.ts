import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class publishBook {
  private apiUrl = 'http://localhost:9090/api/book/post'; 
  constructor(private http: HttpClient) {}

  publishBook(bookData: any): Observable<any> {
    return this.http.post(this.apiUrl, bookData);
  }
}
