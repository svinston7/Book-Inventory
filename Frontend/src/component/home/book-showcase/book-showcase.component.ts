import { Component } from '@angular/core';
import { ShowAllBooksService } from '../../../service/show-all-books.service';
import { Book } from '../Book';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-book-showcase',
  imports: [CommonModule],
  templateUrl: './book-showcase.component.html',
  styleUrl: './book-showcase.component.css'
})
export class BookShowcaswComponent {
dummy(b: any) {
  console.log(b);
}
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
    this.service.showBooks(this.book).subscribe((e)=>{
      this.allBooks=e;
    })
  }
}
