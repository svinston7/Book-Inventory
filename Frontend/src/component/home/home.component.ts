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

  constructor(
    private router:Router,
    private catService:CategoryService
    ){}

    ngOnInit(){
      this.catService.getAllCat().subscribe((e)=>this.allCategory = e)
    }

logout() {
  localStorage.removeItem('token');
  this.router.navigate(['']); 
}

}
