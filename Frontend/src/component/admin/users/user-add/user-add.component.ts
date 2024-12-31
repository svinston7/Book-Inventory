import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { User } from '../../../../model/User';

@Component({
  selector: 'app-user-add',
  templateUrl: './user-add.component.html',
  styleUrls: ['./user-add.component.css']
})
export class UserAddComponent implements OnInit {
  // Initialize user as a plain object that conforms to the User interface
  user: User = {
    userId: 0,
    userName: '',
    firstName: '',
    lastName: '',
    phoneNumber: '',
    password: '',
    roleNumber: 0
  };

  errorMessage: string = ''; // For displaying any error message
  successMessage: string = ''; // For displaying success messages

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {
    // Any initialization logic if needed
  }

  // Method to handle form submission and add a new user
  addUser(): void {
    this.userService.registerUser(this.user).subscribe(
      (response) => {
        this.successMessage = 'User added successfully!';
        this.router.navigate(['/admin/users']);  // Navigate to user list after adding
      },
      (error) => {
        this.errorMessage = 'Failed to add user. Please try again.';
      }
    );
  }
}
