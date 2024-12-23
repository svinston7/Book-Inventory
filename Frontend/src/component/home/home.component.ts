import { Component } from '@angular/core';
import { ShowAllBooksService } from '../../service/show-all-books.service';
import { Book } from './Book';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
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

ngOnInit(){
  this.service.showBooks(this.book).subscribe((e)=>{
    console.log(e)
  })
}
}
