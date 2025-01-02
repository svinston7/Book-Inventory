import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../service/auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule, ɵInternalFormsSharedModule } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '../../../model/User';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-permrole',
  imports: [CommonModule,FormsModule],
  templateUrl: './permrole.component.html',
  styleUrl: './permrole.component.css'
})
export class PermroleComponent implements OnInit{
  users: User[] = [];
  filteredUsers: User[] = [];
  isLoading: boolean = false; // Loading state
  errorMessage: string = ''; // Error message
  isEditing: boolean = false; // Track if we are editing a user
  selectedRoleMap: { [userId: number]: number|null} = {};
  constructor(private authService: AuthService, private router: Router) {
    this.filteredUsers = this.filteredUsers;
  }
  searchTerm: string = '';
  ngOnInit(): void {
    this.fetchAllUsers();
    
  }
  
  fetchAllUsers(): void {
    this.isLoading = true;
    const token = localStorage.getItem('token');
    
    if (token) {
      const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
      
      this.authService.getAllUsers(headers).subscribe(
        (data: User[]) => {
          this.users = data;
          this.filteredUsers = data; 
          this.isLoading = false;
        },
        (error) => {
          console.error('Error fetching users:', error);
          this.isLoading = false;
          this.errorMessage = 'Failed to fetch users.';
        }
      );
    } else {
      this.isLoading = false;
      this.errorMessage = 'No authentication token found.';
    }
   
  
  } 
  
 
  toggleEditMode(user: User): void {
    // Toggle the editing mode for the specific user
    this.isEditing = !this.isEditing;
  
    // Pre-fill the selected role if necessary
    if (this.isEditing) {
      this.selectedRoleMap[user.userId] = user.roleNumber;
    } else {
      // Reset if needed
      delete this.selectedRoleMap[user.userId];
    }
  }
  
  sendData(payload: any) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json' // Ensure JSON Content-Type
    });
  }
  
  // Method to update a user's role
  updateRole(userId: number, role: number): void {
    const token = localStorage.getItem('token');
    
    console.log(role)
    console.log(userId)
    role=Number(role)
    
      this.authService.updateRole(userId, role).subscribe();
   
  }
  
   
  filterUsers(text:string): void {
    
    if (!text) {
      this.filteredUsers = [...this.users];
    } else {
      const lowerSearchTerm = text.toLowerCase();
      this.filteredUsers = this.users.filter(user =>
        user.userName.toLowerCase().includes(lowerSearchTerm)
      );
    }
   
}
  
}