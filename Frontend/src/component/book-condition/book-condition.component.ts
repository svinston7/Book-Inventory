import { Component } from '@angular/core';
import { ConditionService } from '../../service/condtion.service';
import { Inventory } from '../../model/Inventory';
import { ShowAllBooksService } from '../../service/show-all-books.service';
import { Book } from '../../model/Book';
import { Condition } from '../../model/Condition';
import { error } from 'console';
import { HomeComponent } from '../home/home.component';

@Component({
  selector: 'app-book-condition',
  imports: [HomeComponent],
  templateUrl: './book-condition.component.html',
  styleUrl: './book-condition.component.css'
})
export class BookConditionComponent {

invList:Inventory[]=[];
constructor(private conService:ConditionService,private bookService:ShowAllBooksService){}

ngOnInit(){
  this.conService.getInventory().subscribe((e)=>{
    this.invList = e;

    for(let i=0;i<this.invList.length;i++){
      this.bookService.getByISBN(this.invList[i].isbn).subscribe((book:any)=>{
        if(book!=null)
        this.invList[i].book = book
      },
        (error)=>{
          console.log(error)
        }
      )
      this.conService.getRanks(this.invList[i].ranks).subscribe((cond)=>{
        this.invList[i].condition = cond;},
        (error)=>{
          console.log(error)
      })

      console.log(this.invList[i])
    }
    
  },(error)=>{
    console.log(error)
  })
  
}


  
}
