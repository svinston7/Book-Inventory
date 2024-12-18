import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient:HttpClient) { }

  RESTURL:string = "http://localhost:9090/api/user/";

  register(user:any):Observable<any>{
    return this.httpClient.post(this.RESTURL+"register",user,{
      responseType:'text'
    });
  }

  login(user:any):Observable<any>{
    return this.httpClient.post(this.RESTURL+"login",user,{
      responseType:'text'
    })
  }
}
