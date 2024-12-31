import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../../model/User';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private apiUrl = 'http://localhost:9090/api/user';

  constructor(private http: HttpClient) {}

  // Fetch all users
  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/all`);
  }

  // Update user role
  updateUserRole(userId: number, roleNumber: number): Observable<any> {
    return this.http.put(`${this.apiUrl}/update/role/${userId}`, roleNumber);
  }

  // Update user profile (first name, last name, phone number)
  updateUserFirstName(userId: number, firstName: string): Observable<any> {
    return this.http.put(`${this.apiUrl}/update/firstname/${userId}`, firstName);
  }

  updateUserLastName(userId: number, lastName: string): Observable<any> {
    return this.http.put(`${this.apiUrl}/update/lastname/${userId}`, lastName);
  }

  updateUserPhoneNumber(userId: number, phoneNumber: string): Observable<any> {
    return this.http.put(`${this.apiUrl}/update/phonenumber/${userId}`, phoneNumber);
  }

  // Delete user
  deleteUser(userId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/delete/${userId}`);
  }
}
