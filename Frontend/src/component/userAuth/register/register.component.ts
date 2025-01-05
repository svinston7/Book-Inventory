import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { User } from '../../../model/User';
import { AuthService } from '../../../service/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  imports: [FormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  imagePath: string = 'assets/images/book-bg.jpg';
  isPopupVisible = false;
  popupMessage: string = '';
  constructor(private authService: AuthService) {}

  user: User = {
    firstName: '',
    lastName: '',
    phoneNumber: '',
    userName: '',
    password: '',
    terms: false,
    roleNumber: 3,
    userId: 0,
  };

  registerUser(event: Event, registerForm: any) {
    event.preventDefault();

    if (registerForm.valid) {
      // Call the AuthService to register the user
      this.authService.register(this.user).subscribe(
        (response) => {
          // Success handling
          this.popupMessage = 'Your account has been created successfully! 🎉';
          this.isPopupVisible = true;

          // Reset the form after successful registration
          registerForm.resetForm();
          this.resetUserModel();
        },
        (error) => {
          this.popupMessage = 'User Already Exists. Please try again.';
          this.isPopupVisible = true;
        }
      );
    } else {
      // Alert user if form is invalid
      alert('Please fill out all fields correctly.');
    }
  }

  closePopup() {
    this.isPopupVisible = false;
  }

  // Reset user model to initial state
  private resetUserModel() {
    this.user = {
      firstName: '',
      lastName: '',
      phoneNumber: '',
      userName: '',
      password: '',
      terms: false,
      roleNumber: 3,
      userId: 0,
    };
  }
}
