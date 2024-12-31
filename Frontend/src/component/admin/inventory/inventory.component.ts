import { Component, OnInit } from '@angular/core';
import { InventoryService } from '../../../service/service/inventory.service';


@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html'
})
export class InventoryComponent implements OnInit {
  books: any[] = [];

  constructor(private inventoryService: InventoryService) {}

  ngOnInit(): void {
    this.loadInventory();
  }

  loadInventory(): void {
    this.inventoryService.getInventory().subscribe((data) => {
      this.books = data;
    });
  }

  addBook(): void {
    // Navigate to Add Book Form
    alert('Add book form will appear here');
  }

  deleteBook(bookId: number): void {
    this.inventoryService.deleteBook(bookId).subscribe(() => {
      this.loadInventory();
    });
  }
}
