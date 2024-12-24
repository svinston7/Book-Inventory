import { Component } from '@angular/core';
import { ShowAllBooksService } from '../../../service/show-all-books.service';
import { ActivatedRoute } from '@angular/router';
import { Book } from '../../home/Book';
import { CommonModule } from '@angular/common';
import { HomeComponent } from "../../home/home.component";
import { GetPublisherService } from '../../../service/get-publisher.service';
import { Publisher } from '../Publisher';

@Component({
  selector: 'app-view-book',
  imports: [CommonModule, HomeComponent],
  templateUrl: './view-book.component.html',
  styleUrl: './view-book.component.css'
})
export class ViewBookComponent {
  book:Book ={
    isbn: '',
    title: '',
    description: '',
    categoryId: 0,
    edition: '',
    publisherId: 0,
    image: ''
  }
  publisher: Publisher={
    publisherId: 0,
    name: '',
    city: '',
    stateCode: ''
  };
  constructor(
    private bookService:ShowAllBooksService,
    private route:ActivatedRoute,
    private publisherService: GetPublisherService
    ){}

  ngOnInit(){
    this.route.params.subscribe(params => this.getBookByISBN(params['isbn']))
  }


  getBookByISBN(isbn:string){
    this.bookService.getByISBN(isbn).subscribe((data:any)=>{
    this.book = data
    this.getPublisherId(this.book.publisherId);
  })
  }


  getPublisherId(id:number){
    this.publisherService.getPublisherById(id).subscribe(
      (data)=>{
        this.publisher = data;
      }
    )
  }
}
