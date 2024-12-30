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

    selectedCatSet!:Set<number>
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
  filteredBooks:Book[]=[]

  isEmpty():boolean{
    return this.filteredBooks.length===0
  }
  ngOnInit(){
    this.bookService.showBooks().subscribe((e)=>{
      this.allBooks=e;
      this.catService.invokeFunction$.subscribe(() => {  // Subscribe to changes from the CategoryService
        this.filterBooks();
      });
      this.filterBooks();  // Initial filtering based on the current selection
     
    })
  }

  filterBooks() {
    
    this.selectedCatSet = this.catService.getSelectedCategories() || new Set();
    this.filteredBooks = this.allBooks.filter(book => this.selectedCatSet.has(book.categoryId));
    console.log(this.filteredBooks);
  }
}

