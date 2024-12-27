import { Book } from "./Book";

export interface Author{
    authorId:number;
    firstName:string;
    lastName:string;
    photo:string;
    bookList:Book[];
    primary:boolean;
}