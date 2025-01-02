import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { error } from 'console';

@Component({
  selector: 'app-admin-author',
  imports: [CommonModule,FormsModule],
  templateUrl: './admin-author.component.html',
  styleUrl: './admin-author.component.css'
})
export class AdminAuthorComponent {
authors:any[]=[];

showAddAuthorModal:boolean=false;
newAuthor={authorId:0,firstName:'',lastName:''} ;

sucessMessage:string='';
  selectedField: any;
  updateValue: any;
  errorMessage: string | undefined;
constructor(private http:HttpClient){}
ngOnInit():void{
  this.getAuthors();
}
getAuthors():void{
  this.http.get<any[]>('http://localhost:9090/api/author/allauthors').subscribe({
    next:(data)=>{
      this.authors=data;
      console.log('author data fetched');
    },
    error:(error)=>{
      console.error('error fetching author');
    },
  });
}
closeModal():void{
  this.showAddAuthorModal=false;
  this.newAuthor={authorId:0,firstName:'',lastName:''}
}
openAddAuthorModal():void{
  this.showAddAuthorModal=true;
  console.log("open");
}
addAuthor():void{
  const authorExists = this.authors.some(author => author.authorId === this.newAuthor.authorId);

  if (authorExists) {
    this.sucessMessage = '';  // Clear any success message
    this.errorMessage = 'Author ID already exists. Please choose a different ID.';  // Set error message
    return;  // Stop the execution of the function if the ID exists
  }

  const payload={...this.newAuthor};
  this.http.post('http://localhost:9090/api/author/post',payload).subscribe({
    next:()=>{
      console.log("author added sucessfully");
      this.sucessMessage='Author has been added sucessfully';
      console.log(this.sucessMessage);
      this.closeModal();
      this.getAuthors();

    },
  


  })
}

updateAuthor(author:any):void{
  if(!author.selectedField||!author.updateValue){
  }
  let updateurl='';
  switch(author.selectedField){
    case 'firstName':
      updateurl=`http://localhost:9090/api/author/firstname/${author.authorId}`;
      break;
    case 'lastName':
      updateurl=`http://localhost:9090/api/author/lastname/${author.authorId}`;
      break;
      default:
        alert("invalid field selected");
        return;
  }
  this.http.put(updateurl,author.updateValue).subscribe({
    next: () => {
      alert(`${author.selectedField} updated successfully!`);
      // this.selectedField = '';
      // this.updateValue = '';
      this.getAuthors(); // Refresh the list
    },
    error: (error) => {
      alert(`Error updating ${this.selectedField}: ${error}`);
    }
  });
}
}
