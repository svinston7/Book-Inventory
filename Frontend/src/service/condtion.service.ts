import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConditionService {

  INVENTORYURL:string = "http://localhost:9090/api/inventory/"
  CONDITIONURL:string = "http://localhost:9090/api/bookcondition/"
  constructor(private http:HttpClient) { }

  getInventory():Observable<any>{
    return this.http.get(this.INVENTORYURL+"getall");
  }

  getRanks(rank:number):Observable<any>{
    return this.http.get(this.CONDITIONURL+rank);
  }

}
