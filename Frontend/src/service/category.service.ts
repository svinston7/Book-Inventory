  import { HttpClient } from '@angular/common/http';
  import { Injectable } from '@angular/core';
  import { Observable } from 'rxjs';

  @Injectable({
    providedIn: 'root'
  })
  export class CategoryService {

    constructor(private httpClient:HttpClient) { }
    CATURL:string = "http://localhost:9090/api/category/"

    getCategory(catId:number):Observable<any>{
      return this.httpClient.get(this.CATURL + catId);
    }

    getAllCat():Observable<any>{
      return this.httpClient.get(this.CATURL+"getall")
    }

  }
