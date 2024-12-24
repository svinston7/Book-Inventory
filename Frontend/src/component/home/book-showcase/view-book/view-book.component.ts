import { Component } from '@angular/core';
import { ShowAllBooksService } from '../../../../service/show-all-books.service';
import { ActivatedRoute } from '@angular/router';
import { Book } from '../../Book';
import { CommonModule } from '@angular/common';
import { HomeComponent } from "../../home.component";

@Component({
  selector: 'app-view-book',
  imports: [CommonModule, HomeComponent],
  templateUrl: './view-book.component.html',
  styleUrl: './view-book.component.css'
})
export class ViewBookComponent {
  isReadonly = true;
  constructor(private service:ShowAllBooksService,private route:ActivatedRoute){}

  ngOnInit(){
    this.route.params.subscribe(params => this.getBookByISBN(params['isbn']))
  }

  book:Book | undefined

  getBookByISBN(isbn:string){
    this.service.getByISBN(isbn).subscribe((data:any)=>this.book = data)
  }
}
