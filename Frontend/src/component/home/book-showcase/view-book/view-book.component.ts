import { Component } from '@angular/core';
import { ShowAllBooksService } from '../../../../service/show-all-books.service';
import { ActivatedRoute } from '@angular/router';
import { Book } from '../../Book';
import { CommonModule } from '@angular/common';
import { HomeComponent } from "../../home.component";
import { NavbarComponent } from "../../../navbar/navbar.component";
import { GetPublisherService } from '../../../../service/get-publisher.service';
import { Publisher } from './Publisher';

@Component({
  selector: 'app-view-book',
  imports: [CommonModule, HomeComponent, NavbarComponent],
  templateUrl: './view-book.component.html',
  styleUrl: './view-book.component.css'
})
export class ViewBookComponent {
  book:Book | undefined
  publisher:Publisher={
    publisherId: 0,
    name: '',
    city: '',
    stateCode: ''
  }
  pubList:Publisher[]=[]
  isReadonly = true;
  constructor(private allBookService:ShowAllBooksService,private route:ActivatedRoute,private publisherService:GetPublisherService){}
  
  ngOnInit(){
    this.route.params.subscribe(params => this.getBookByISBN(params['isbn']))
    // this.getPublisherById(this.publisher.publisherId);
    this.getAllPublishers()
    
  }

  

  getBookByISBN(isbn:string){
    this.allBookService.getByISBN(isbn).subscribe((data:any)=>this.book = data)
  }

getAllPublishers(){
  this.publisherService.showPublishers().subscribe((e)=>{
    this.pubList=e;
  })
}

  getPublisherById(id:number){
    this.publisherService.getPublisherById(id).subscribe((data:any)=>this.publisher = data)
   
  }

  showConsole(){
    console.log(this.pubList);
  }
}
