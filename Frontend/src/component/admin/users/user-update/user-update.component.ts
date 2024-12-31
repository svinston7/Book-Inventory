import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../user.service';
import { User } from '../../../../model/User';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {
  user: User | undefined;  // User to be updated
  userId: number = 0;       // Store user ID from route params
  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Fetch user ID from route params
    this.userId = this.route.snapshot.params['id'];
    this.fetchUserDetails();
  }

  // Fetch current user details by ID
  fetchUserDetails(): void {
    this.userService.getUserById(this.userId).subscribe(
      (data: User) => {
        this.user = data;
      },
      (error) => {
        this.errorMessage = 'Failed to load user details.';
      }
    );
  }

  // Submit updated user data
  updateUser(): void {
    if (this.user) {
      this.userService.updateUser(this.userId, this.user).subscribe(
        () => {
          this.successMessage = 'User updated successfully!';
          this.router.navigate(['/admin/users']);
        },
        (error) => {
          this.errorMessage = 'Failed to update user. Please try again.';
        }
      );
    }
  }
}
