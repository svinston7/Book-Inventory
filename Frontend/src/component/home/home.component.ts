import { Component } from '@angular/core';

import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ShoppingcartComponent } from '../shoppingcart/shoppingcart.component';
import { Category } from '../../model/Category';
import { CategoryService } from '../../service/category.service';
import { User } from '../../model/User';
import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-home',
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  
  cat :Category={
    catId: 0,
    catDescription: ''
  }
allCategory:Category[]=[]
selectedCategories: Set<number> = new Set();
user:User={
  firstName: '',
  lastName: '',
  phoneNumber: '',
  userName: '',
  password: '',
  roleNumber: 0
}

  constructor(
    private router:Router,
    private catService:CategoryService,
    private userService:AuthService
    ){}

    ngOnInit(){
      const userName = localStorage.getItem('userName')
      this.catService.getAllCat().subscribe((e)=>{
        this.allCategory = e
        if(userName){
          this.userService.getUserByUserName(userName).subscribe((data)=>{
            this.user = data
            })
        }
      })
    }

    onCheckboxChange(catId: number, event: Event) {
      const input = event.target as HTMLInputElement; // Cast the event target to HTMLInputElement
      if (input) { // Check that the input is not null
        const isChecked = input.checked;
        if (isChecked) {
          this.selectedCategories.add(catId);  // Add selected category ID
        } else {
          this.selectedCategories.delete(catId);  // Remove unselected category ID
        }
        
      }
    }
logout() {
  localStorage.clear()
  this.router.navigate(['']); 
}
filter() {

  this.catService.setSelectedCategories(this.selectedCategories);
  // Here you can process the selected category IDs or category objects
}
  
onSearch(event: Event): void {
  event.preventDefault();
  const input = event.target as HTMLInputElement; // Cast to HTMLInputElement
  const query = input.value; // Access the 'value' property safely
  this.catService.setSearchQuery(query); // Emit the search query
}

}
