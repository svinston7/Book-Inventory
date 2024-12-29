import { Component } from '@angular/core';
import { ShowAllBooksService } from '../../service/show-all-books.service';
import { Book } from '../../model/Book';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Category } from '../../model/Category';
import { CategoryService } from '../../service/category.service';

@Component({
  selector: 'app-book-showcase',
  imports: [CommonModule,RouterModule],
  templateUrl: './book-showcase.component.html',
  styleUrl: './book-showcase.component.css'
})
export class BookShowcaseComponent {

  constructor(
    private bookService:ShowAllBooksService,
    private catService:CategoryService
    
    ){}

  book:Book={
    isbn: '',
    title: '',
    description: '',
    categoryId: 0,
    edition: '',
    publisherId: 0,
    image: ''
  }
  cat :Category={
    catId: 0,
    catDescription: ''
  }
  allBooks:Book[]=[];
  allCategory:Category[]=[]
  ngOnInit(){
    this.bookService.showBooks().subscribe((e)=>{
      this.allBooks=e;
      this.catService.getAllCat().subscribe((e)=>{
        this.allCategory = e;
      });
    })
  }
}
