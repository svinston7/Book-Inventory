import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Inventory } from '../../../../model/Inventory';
import { InventoryService } from '../../../../service/service/inventory.service';

@Component({
  selector: 'app-updatebook',
  templateUrl: './updatebook.component.html',
  styleUrls: ['./updatebook.component.css']
})
export class UpdateBookComponent implements OnInit {
  selectedInventory: Inventory = {
    inventoryId: 0,
    isbn: '',
    ranks: 0,
    purchased: false,
    book: null
  };

  constructor(
    private inventoryService: InventoryService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Get the inventory ID from the URL and fetch the details
    const inventoryId = this.route.snapshot.paramMap.get('inventoryId');
    if (inventoryId) {
      this.fetchInventoryById(+inventoryId);
    }
  }

  // Fetch inventory details by ID
  fetchInventoryById(id: number): void {
    this.inventoryService.getInventoryById(id).subscribe(
      (inventory) => {
        this.selectedInventory = inventory;
      },
      (error) => {
        alert('Inventory not found.');
        this.router.navigate(['/admin/inventory']);
      }
    );
  }

  // Update the inventory item
  updateInventory(): void {
    this.inventoryService.updateInventory(this.selectedInventory).subscribe(
      () => {
        alert('Inventory updated successfully!');
        this.router.navigate(['/admin/inventory']); // Navigate back to the inventory list
      },
      (error) => {
        alert('Failed to update inventory.');
      }
    );
  }
}
