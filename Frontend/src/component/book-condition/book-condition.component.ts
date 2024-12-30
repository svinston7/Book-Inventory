import { Component } from '@angular/core';
import { ConditionService } from '../../service/condtion.service';
import { Inventory } from '../../model/Inventory';
import { ShowAllBooksService } from '../../service/show-all-books.service';
import { Book } from '../../model/Book';
import { Condition } from '../../model/Condition';

@Component({
  selector: 'app-book-condition',
  imports: [],
  templateUrl: './book-condition.component.html',
  styleUrl: './book-condition.component.css'
})
export class BookConditionComponent {
//   book:Book={
//     isbn: '',
//     title: '',
//     description: '',
//     categoryId: 0,
//     edition: '',
//     publisherId: 0,
//     image: ''
//   }
//   condition:Condition={
//     ranks: 0,
//     description: '',
//     fullDescription: '',
//     price: 0
//   }
// inv:Inventory={
//   isbn: '',
//   ranks: 0,
//   purchased: false,
//   book: this.book,
//   condition: this.condition
// }
invList:Inventory[]=[];
constructor(private conService:ConditionService,private bookService:ShowAllBooksService){}

ngOnInit(){
  this.conService.getInventory().subscribe((e)=>{
    this.invList = e;

    for(let i=0;i<this.invList.length;i++){
      this.bookService.getByISBN(this.invList[i].isbn).subscribe((book:any)=>{
        this.invList[i].book = book
      })
      this.conService.getRanks(this.invList[i].ranks).subscribe((cond)=>{
        this.invList[i].condition = cond;
      })

      console.log(this.invList[i])
    }
    // this.invList.forEach((inv)=>{
    //   this.bookService.getByISBN(inv.isbn).subscribe((bookData:any)=>{
    //     this.invList = bookData;
        
    //   })
    // })
    // this.invList.forEach((con)=>{
    //   this.conService.getRanks(con.ranks).subscribe((cond)=>{
    //     this.condition=cond;
    //   })
    // })
  })
  
}

getInventory(){

}
  
}
