import { Book } from "./Book";
import { Condition } from "./Condition";

export interface Inventory{
  inventoryId:number
  book:Book;
  condition:Condition;
  isbn: string;
  ranks: number;
  purchased: boolean;
}