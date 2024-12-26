import { Component } from '@angular/core';
import { ShowAllBooksService } from '../../service/show-all-books.service';
import { Book } from '../../model/Book';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-book-showcase',
  imports: [CommonModule,RouterModule],
  templateUrl: './book-showcase.component.html',
  styleUrl: './book-showcase.component.css'
})
export class BookShowcaseComponent {

  constructor(private service:ShowAllBooksService){}

  book:Book={
    isbn: '',
    title: '',
    description: '',
    categoryId: 0,
    edition: '',
    publisherId: 0,
    image: ''
  }
  
  allBooks:Book[]=[];

  ngOnInit(){
    this.service.showBooks().subscribe((e)=>{
      this.allBooks=e;
    })
  }
}
