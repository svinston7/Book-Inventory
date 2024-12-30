import { Component } from '@angular/core';

import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ShoppingcartComponent } from '../shoppingcart/shoppingcart.component';
import { Category } from '../../model/Category';
import { CategoryService } from '../../service/category.service';

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


  constructor(
    private router:Router,
    private catService:CategoryService
    ){}

    ngOnInit(){
      this.catService.getAllCat().subscribe((e)=>this.allCategory = e)
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
  localStorage.removeItem('token');
  this.router.navigate(['']); 
}
filter() {

  this.catService.setSelectedCategories(this.selectedCategories);
  // Here you can process the selected category IDs or category objects
}
  
onSearch(event: Event): void {
  event.preventDefault();
  console.log('inviked')
  const input = event.target as HTMLInputElement; // Cast to HTMLInputElement
  const query = input.value; // Access the 'value' property safely
  this.catService.setSearchQuery(query); // Emit the search query
}

}
