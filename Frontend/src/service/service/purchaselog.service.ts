import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
  import { PurchaseLog } from '../../model/PurchaseLog';


@Injectable({
  providedIn: 'root'
})
export class PurchaseLogService {

  private apiUrl = `http://localhost:9090/api/purchaselog`;

  constructor(private http: HttpClient) { }

  // POST: Add a purchase log
  addPurchaseLog(purchaseLog: PurchaseLog): Observable<any> {
    return this.http.post(`${this.apiUrl}/post`, purchaseLog, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  // GET: Retrieve purchase log by userId
  getPurchaseLog(userId: number): Observable<PurchaseLog> {
    return this.http.get<PurchaseLog>(`${this.apiUrl}/${userId}`);
  }

  // PUT: Update purchase log with new inventoryId
  updatePurchaseLog(userId: number, inventoryId: number): Observable<any> {
    return this.http.put(`${this.apiUrl}/update/inventoryId/${userId}`, inventoryId, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }
}
