import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Inventory } from '../../../model/Inventory';
import { InventoryService } from '../../../service/service/inventory.service';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements OnInit {
  newInventory: Inventory = {
    inventoryId: 0,
    isbn: '',
    ranks: 0,
    purchased: false,
    book: null
  };

  selectedInventory: Inventory | null = null;

  searchId: number = 0;
  inventories: Inventory[] = [];

  constructor(
    private inventoryService: InventoryService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.fetchInventoryList();
  }

  // Fetch all inventory items
  fetchInventoryList(): void {
    this.inventoryService.getInventoryList().subscribe(
      (inventoryList) => {
        this.inventories = inventoryList;
      },
      (error) => {
        alert('Failed to fetch inventory list.');
      }
    );
  }

  // Add new inventory item
  addInventory(): void {
    this.inventoryService.addInventory(this.newInventory).subscribe(
      (response: any) => {
        alert(response.message);
        this.resetForm();
        this.fetchInventoryList(); // Refresh the inventory list
      },
      (error) => {
        alert('Failed to add inventory. Please try again.');
      }
    );
  }

  // Delete an inventory item by ID
  deleteInventory(inventoryId: number): void {
    this.inventoryService.deleteInventory(inventoryId).subscribe(
      () => {
        alert('Inventory item deleted successfully!');
        this.fetchInventoryList(); // Refresh the inventory list after deletion
      },
      (error) => {
        alert('Failed to delete inventory item.');
      }
    );
  }

  // Reset the form model for adding new inventory
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
