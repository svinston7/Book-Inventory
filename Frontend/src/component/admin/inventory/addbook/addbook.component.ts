import { Component } from '@angular/core';
import { Inventory } from '../../../../model/Inventory';
import { InventoryService } from '../../../../service/service/inventory.service';

@Component({
  selector: 'app-addbook',
  templateUrl: './addbook.component.html',
  styleUrls: ['./addbook.component.css']
})
export class AddbookComponent {
  // Model for the new inventory item (Book)
  newInventory: Inventory = {
    inventoryId: 0,
    isbn: '',
    ranks: 0,
    purchased: false,
    book: null
  };

  constructor(private inventoryService: InventoryService) {}

  // Method to add the new inventory item (Book)
  addInventory() {
    this.inventoryService.addInventory(this.newInventory).subscribe(
      (response) => {
        alert('Inventory added successfully!');
        // Reset the form after successful addition
        this.resetForm();
      },
      (error) => {
        alert('Failed to add inventory. Please try again.');
      }
    );
  }

  // Reset the form fields after adding inventory
  resetForm(): void {
    this.newInventory = {
      inventoryId: 0,
      isbn: '',
      ranks: 0,
      purchased: false,
      book: null
    };
  }
}
