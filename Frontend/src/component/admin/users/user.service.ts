import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { User } from '../../../model/User';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly BASE_URL = 'http://localhost:9090/api/user';  // Base API URL

  constructor(private http: HttpClient) {}

  // Register a new user
  registerUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.BASE_URL}/register`, user)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Fetch all users
  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.BASE_URL}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Delete a user by ID
  deleteUser(userId: number): Observable<void> {
    return this.http.delete<void>(`${this.BASE_URL}/delete/${userId}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Fetch user by ID
  getUserById(userId: number): Observable<User> {
    return this.http.get<User>(`${this.BASE_URL}/${userId}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Update user by ID
  updateUser(userId: number, user: User): Observable<User> {
    return this.http.put<User>(`${this.BASE_URL}/update/${userId}`, user)
      .pipe(
        catchError(this.handleError)
      );
  }

  // Error handling method
  private handleError(error: HttpErrorResponse): Observable<never> {
    let errorMessage = 'An unknown error occurred.';
    if (error.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Backend error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.error(errorMessage);
    return throwError(() => new Error(errorMessage));
  }
}
