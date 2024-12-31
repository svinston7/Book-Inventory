import { Component, OnInit } from '@angular/core';
import { Role } from '../../../model/Role';
import { RoleService } from '../../../service/service/role.service';



@Component({
  selector: 'app-role',
  templateUrl: './roles.component.html',
  styleUrls: ['./roles.component.css']
})
export class RoleComponent implements OnInit {

  roles: Role[] = [];
  role: Role = { roleId: 0, roleName: '', roleDescription: '' };  // Initial values
  successMessage: string = '';
  errorMessage: string = '';

  constructor(private roleService: RoleService) { }

  ngOnInit(): void {
    this.loadRoles();  // Load roles on component initialization
  }

  // Load all roles
  loadRoles(): void {
    this.roleService.getRoles().subscribe(
      data => {
        this.roles = data;
        this.errorMessage = '';
      },
      error => {
        this.errorMessage = 'Error fetching roles';
        this.roles = [];
      }
    );
  }

  // Add a new role
  addRole(): void {
    this.roleService.addRole(this.role).subscribe(
      response => {
        this.successMessage = 'Role added successfully';
        this.errorMessage = '';
        this.loadRoles();  // Refresh roles list
      },
      error => {
        this.errorMessage = 'Error adding role';
        this.successMessage = '';
      }
    );
  }

  // Update an existing role
  updateRole(): void {
    this.roleService.updateRole(this.role.roleId, this.role).subscribe(
      response => {
        this.successMessage = 'Role updated successfully';
        this.errorMessage = '';
        this.loadRoles();  // Refresh roles list
      },
      error => {
        this.errorMessage = 'Error updating role';
        this.successMessage = '';
      }
    );
  }
  editRole(selectedRole: any) {
    this.role = { ...selectedRole };  // Spread operation here
  }
  

  // Delete a role
  deleteRole(roleId: number): void {
    this.roleService.deleteRole(roleId).subscribe(
      response => {
        this.successMessage = 'Role deleted successfully';
        this.errorMessage = '';
        this.loadRoles();  // Refresh roles list
      },
      error => {
        this.errorMessage = 'Error deleting role';
        this.successMessage = '';
      }
    );
  }
}
