import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';import { CanActivate, Router } from '@angular/router';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class InventoryService {
  private apiUrl = 'http://localhost:9090/api/inventory';  
 
  constructor(private http: HttpClient) {}
 
  getInventory(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}`);  
  }
 
  deleteBook(bookId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${bookId}`);
  }
}

