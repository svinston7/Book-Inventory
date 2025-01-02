import { Component } from '@angular/core';
import { AuthService } from '../../service/auth.service';
import { User } from '../../model/User';
import { HomeComponent } from '../home/home.component';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  imports: [HomeComponent,CommonModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  user:User={
    userId:0,
    firstName: '',
    lastName: '',
    phoneNumber: '',
    userName: '',
    password: '',
    role:{
      roleNumber:3,
      roleName:''
    },  }
  userId: number=this.user.userId;
  FirstName: string = this.user.firstName; // Default first name
  LastName: string = this.user.lastName;       // Default last name
  FullName: string = `${this.FirstName} ${this.LastName}`; // Default full name
  Contact: string = this.user.phoneNumber; 
  UserName: string=this.user.userName// Default contact
  avatarUrl: string = '/assets/profile.png';
  isEditing: boolean = false;   // Control editing state
  constructor(private authService:AuthService,private router:Router){}

  ngOnInit(){
    const name  = localStorage.getItem('userName');
    if(name){
    this.authService.getUser(name).subscribe((e)=>{
      this.user=e;
      console.log(e)

      this.FirstName = this.user.firstName; 
      this.LastName=this.user.lastName;
      this.FullName=`${this.user.firstName} ${this.user.lastName}`;
      this.Contact=this.user.phoneNumber;
      this.UserName=this.user.userName;
    })
  }
  }

  toggleEdit() {
    this.isEditing = !this.isEditing;

    if (!this.isEditing) {
      // Save logic when exiting edit mode
      this.updateFullName();
      this.updateProfile();
      console.log('Profile saved:', {
        FirstName: this.FirstName,
        LastName: this.LastName,
        FullName: this.FullName,
        Contact: this.Contact,

      });
    }
  }

  onFirstNameChange(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    this.FirstName = inputElement.value;
    this.updateFullName();
  }

  onLastNameChange(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    this.LastName = inputElement.value;
    this.updateFullName();
  }

  onContactChange(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    this.Contact = inputElement.value;
  }

  updateFullName() {
    this.FullName = `${this.FirstName} ${this.LastName}`;
  }

  onAvatarChange(event: Event) {
    const inputElement = event.target as HTMLInputElement;
    const file = inputElement.files?.[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        this.avatarUrl = reader.result as string;
      };
      reader.readAsDataURL(file);
    }
  }
  updateProfile() {
    const userId = this.user.userId;

    if (this.UserName === null) {
      console.error('User ID is not set');
      return;
    }

    this.updateFirstName(userId, this.FirstName); 
    this.updateLastName(userId, this.LastName);
    this.updatePhoneNumber(userId, this.Contact);
  }

  updateFirstName(userId: number, newFirstName: string) {
    if (newFirstName !== this.user.firstName) {
      console.log('Updating First Name');
      this.authService.updateFirstName(userId, newFirstName).subscribe({
        next: (response) => {
          console.log('First name updated successfully', response);
        },
        error: (err) => {
          console.error('Error updating first name:', err);
        },
      });
    }
  }

  updateLastName(userId: number, newLastName: string) {
    if (newLastName !== this.user.lastName) {
      console.log('Updating Last Name');
      this.authService.updateLastName(userId, newLastName).subscribe({
        next: (response) => {
          console.log('Last name updated successfully', response);

        },
        error: (err) => {
          console.error('Error updating last name:', err);
        },
      });
    }
  }

  updatePhoneNumber(userId: number, newPhoneNumber: string) {
    if (newPhoneNumber !== this.user.phoneNumber) {
      console.log('Updating Phone Number');
      this.authService.updatePhoneNumber(userId, newPhoneNumber).subscribe({
        next: (response) => {
          console.log('Phone number updated successfully', response);
        },
        error: (err) => {
          console.error('Error updating phone number:', err);
        },
      });
    }
  }
}
