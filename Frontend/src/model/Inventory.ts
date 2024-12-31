import { Book } from "./Book";


export interface Inventory {
  inventoryId: number;
  isbn: string;
  ranks: number;
  purchased: boolean;
  book: Book | null;
}
