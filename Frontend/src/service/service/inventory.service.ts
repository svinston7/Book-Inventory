import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Inventory } from '../../model/Inventory';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {
  private apiUrl = 'http://localhost:9090/api/inventory';

  constructor(private http: HttpClient) {}

  // Fetch all inventories
  getInventoryList(): Observable<Inventory[]> {
    return this.http.get<Inventory[]>(this.apiUrl);
  }

  // Fetch specific inventory by ID
  getInventoryById(id: number): Observable<Inventory> {
    return this.http.get<Inventory>(`${this.apiUrl}/${id}`);
  }

  // Add a new inventory
  addInventory(inventory: Inventory): Observable<Inventory> {
    return this.http.post<Inventory>(this.apiUrl, inventory);
  }

  // Update an inventory
  updateInventory(inventory: Inventory): Observable<Inventory> {
    return this.http.put<Inventory>(`${this.apiUrl}/${inventory.inventoryId}`, inventory);
  }

  // Update the purchased status of an inventory
  updatePurchased(inventoryId: number, purchased: boolean): Observable<any> {
    return this.http.put(`http://localhost:9090/api/inventory/update/purchased/${inventoryId}`, purchased);
  }

  // Delete an inventory by ID
  deleteInventory(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
