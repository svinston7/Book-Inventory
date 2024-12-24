import { Component } from '@angular/core';
import { ShowAllBooksService } from '../../../../service/show-all-books.service';
import { ActivatedRoute } from '@angular/router';
import { Book } from '../../Book';

@Component({
  selector: 'app-view-book',
  imports: [],
  templateUrl: './view-book.component.html',
  styleUrl: './view-book.component.css'
})
export class ViewBookComponent {

  constructor(private service:ShowAllBooksService,private route:ActivatedRoute){}

  ngOnInit(){
    this.route.params.subscribe(params => this.getBookByISBN(params['isbn']))
  }

  book:Book | undefined

  getBookByISBN(isbn:string){
    this.service.getByISBN(isbn).subscribe((data:any)=>this.book = data)
  }
}
