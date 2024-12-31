import { Component, OnInit } from '@angular/core';
import { User } from '../../../model/User';
import { UserService } from '../../../service/service/user.service';

  // Adjust path as needed

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user: User = { userId: 0, userName: '', firstName: '', lastName: '', phoneNumber: '', password: '', roleNumber: 0 };
  loginData: User = { userId: 0, userName: '', firstName: '', lastName: '', phoneNumber: '', password: '', roleNumber: 0 };
  loginMessage: string = '';
  successMessage: string = '';
  errorMessage: string = '';

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    // Initialization code
  }

  // Register a new user
  register(): void {
    this.userService.registerUser(this.user).subscribe(
      response => {
        this.successMessage = 'User registered successfully!';
        this.errorMessage = '';
      },
      error => {
        this.errorMessage = 'Error registering user.';
        this.successMessage = '';
      }
    );
  }

  // Login user
  login(): void {
    this.userService.loginUser(this.loginData).subscribe(
      response => {
        this.loginMessage = `Login successful! Token: ${response}`;
        this.errorMessage = '';
      },
      error => {
        this.loginMessage = '';
        this.errorMessage = 'Invalid credentials';
      }
    );
  }

  // Update user first name
  updateFirstName(): void {
    this.userService.updateFirstName(this.user.userId, this.user.firstName).subscribe(
      response => {
        this.successMessage = 'First name updated successfully';
        this.errorMessage = '';
      },
      error => {
        this.errorMessage = 'Error updating first name';
        this.successMessage = '';
      }
    );
  }

  // Update user last name
  updateLastName(): void {
    this.userService.updateLastName(this.user.userId, this.user.lastName).subscribe(
      response => {
        this.successMessage = 'Last name updated successfully';
        this.errorMessage = '';
      },
      error => {
        this.errorMessage = 'Error updating last name';
        this.successMessage = '';
      }
    );
  }

  // Update user phone number
  updatePhoneNumber(): void {
    this.userService.updatePhoneNumber(this.user.userId, this.user.phoneNumber).subscribe(
      response => {
        this.successMessage = 'Phone number updated successfully';
        this.errorMessage = '';
      },
      error => {
        this.errorMessage = 'Error updating phone number';
        this.successMessage = '';
      }
    );
  }
}
