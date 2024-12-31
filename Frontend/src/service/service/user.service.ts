import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { User } from '../../model/User';  // Adjust path as needed

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = `http://localhost:9090/api/user`;  // Update with your backend API URL

  constructor(private http: HttpClient) { }

  // POST: Register a new user
  registerUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.apiUrl}/register`, user, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  // POST: Login a user
  loginUser(user: User): Observable<string> {
    return this.http.post<string>(`${this.apiUrl}/login`, user, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  // GET: Retrieve user by username
  getUserByUsername(username: string): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${username}`);
  }

  // PUT: Update user first name
  updateFirstName(userId: number, firstName: string): Observable<any> {
    return this.http.put(`${this.apiUrl}/update/firstname/${userId}`, firstName, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  // PUT: Update user last name
  updateLastName(userId: number, lastName: string): Observable<any> {
    return this.http.put(`${this.apiUrl}/update/lastname/${userId}`, lastName, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  // PUT: Update user phone number
  updatePhoneNumber(userId: number, phoneNumber: string): Observable<any> {
    return this.http.put(`${this.apiUrl}/update/phonenumber/${userId}`, phoneNumber, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }
}
