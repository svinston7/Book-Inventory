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

  getUserByUserName(username:string):Observable<any>{
    return this.httpClient.get(this.RESTURL+username)
  }

  getUser(username:string):Observable<any>{
    return this.httpClient.get(this.RESTURL+username)
  }




  updateFirstName(userId: number, firstName: string): Observable<any> {

    return this.httpClient.put(`${this.RESTURL}update/firstname/${userId}`, firstName);
  }

  updateLastName(userId: number, lastName: string): Observable<any> {
    return this.httpClient.put(`${this.RESTURL}update/lastname/${userId}`, lastName);
  }


  updatePhoneNumber(userId: number, phoneNumber: string): Observable<any> {
    return this.httpClient.put(`${this.RESTURL}update/phonenumber/${userId}`, phoneNumber);
  }
}
