import { Component, OnInit } from '@angular/core';
import { User } from '../../../../model/User';
import { UserService } from '../user.service';


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: User[] = []; // Array to hold the list of users
  errorMessage: string = ''; // To show any error messages

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.getUsers(); // Fetch the list of users when component is initialized
  }

  // Fetch users from the backend using the service
  getUsers(): void {
    this.userService.getAllUsers().subscribe(
      (response: User[]) => {
        this.users = response; // Assign the response to the users array
      },
      (error) => {
        this.errorMessage = 'Failed to fetch users. Please try again later.';
      }
    );
  }

  // Method to delete a user
  deleteUser(userId: number): void {
    if (confirm('Are you sure you want to delete this user?')) {
      this.userService.deleteUser(userId).subscribe(
        () => {
          alert('User deleted successfully!');
          this.getUsers(); // Re-fetch the list after deletion
        },
        (error) => {
          alert('Failed to delete user. Please try again.');
        }
      );
    }
  }

  // Method to navigate to update user page (you need to implement routing for this)
  editUser(userId: number): void {
    // Logic to navigate to update user page, for example using the Angular router
    // this.router.navigate(['/admin/users/update', userId]);
  }
}
