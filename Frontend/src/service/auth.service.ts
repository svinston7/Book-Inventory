import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private searchQuerySubject = new BehaviorSubject<string>('');
searchQuery$ = this.searchQuerySubject.asObservable();
  setSearchQuery(query: string) {
    this.searchQuerySubject.next(query);
  }
  
  constructor(private httpClient:HttpClient) { }

  RESTURL:string = "http://localhost:9090/api/user/";
  ROLEURL:String = "http://localhost:9090/api/permrole/"
  isAuthenticated= false;
   // Get all users
   getAllUsers(headers: unknown): Observable<any[]> {
    return this.httpClient.get<any[]>(`${this.RESTURL}all`);
  }
  
  register(user:any):Observable<any>{
    return this.httpClient.post(this.RESTURL+"register",user,{
      responseType:'text'
    });
  }

  login(user:any):Observable<any>{
    this.isAuthenticated = true;
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

  updateRole(userId: number, role: number): Observable<any>{
    return this.httpClient.put(`${this.RESTURL}update/role/${userId}`, role,{responseType:'json'});
  }

  getRole(role:number):Observable<any>{
    return this.httpClient.get(this.ROLEURL+"rolenumber/"+role)
  }
}
