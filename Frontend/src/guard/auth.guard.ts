import { CanActivate, CanActivateFn, Router, UrlTree } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

export const authGuard: CanActivateFn = (route, state) => {
  return true;
};
@Injectable({
  providedIn: 'root'  // Make sure the guard is provided in the root injector
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate():
    | boolean
    | UrlTree
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree> {
    // Check if the user is authenticated
    const isLoggedIn = localStorage.getItem('token')
    if (isLoggedIn) {
      return true; // Allow access
    } else {
      // Redirect to login if not authenticated
      return false;
    }
  }
}
