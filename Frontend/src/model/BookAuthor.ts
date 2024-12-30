import { Book } from "./Book";

export interface BookAuthor{
    id:number;
    isbn:String;
    authorId:number;
    primaryAuthor:boolean;
    BookList:Book[]
}