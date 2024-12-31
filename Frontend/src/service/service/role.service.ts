import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Role } from '../../model/Role';


@Injectable({
  providedIn: 'root'
})
export class RoleService {

  private apiUrl = `http//localhost:9090/api/permrole`;  // Update this with your backend API URL

  constructor(private http: HttpClient) { }

  // POST: Add a new role
  addRole(role: Role): Observable<any> {
    return this.http.post(`${this.apiUrl}/post`, role, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  // GET: Retrieve all roles
  getRoles(): Observable<Role[]> {
    return this.http.get<Role[]>(`${this.apiUrl}`);
  }

  // PUT: Update an existing role
  updateRole(roleId: number, role: Role): Observable<any> {
    return this.http.put(`${this.apiUrl}/update/${roleId}`, role, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  // DELETE: Remove a role by ID
  deleteRole(roleId: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/delete/${roleId}`);
  }
}
