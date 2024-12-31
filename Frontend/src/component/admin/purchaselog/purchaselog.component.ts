import { Component, OnInit } from '@angular/core';

import { PurchaseLog } from '../../../model/PurchaseLog';
import { PurchaseLogService } from '../../../service/service/purchaselog.service';
import { FormsModule } from '@angular/forms';


@Component({
  selector: 'app-purchase-log',
  imports:[FormsModule],
  templateUrl: './purchaselog.component.html',
  styleUrls: ['./purchaselog.component.css']
})
export class PurchaseLogComponent implements OnInit {

  purchaseLog: PurchaseLog = { userId: 0, inventoryId: 0 }; // Initialize with empty values
  purchaseLogDetails: PurchaseLog | null = null;
  successMessage: string = '';
  errorMessage: string = '';

  constructor(private purchaseLogService: PurchaseLogService) { }

  ngOnInit(): void { }

  // Add purchase log
  addPurchaseLog(): void {
    this.purchaseLogService.addPurchaseLog(this.purchaseLog).subscribe(
      response => {
        this.successMessage = response.message;
        this.errorMessage = '';
      },
      error => {
        this.errorMessage = error.error.message;
        this.successMessage = '';
      }
    );
  }

  // Retrieve purchase log by userId
  getPurchaseLog(): void {
    this.purchaseLogService.getPurchaseLog(this.purchaseLog.userId).subscribe(
      data => {
        this.purchaseLogDetails = data;
        this.errorMessage = '';
      },
      error => {
        this.errorMessage = 'Error fetching purchase log';
        this.purchaseLogDetails = null;
      }
    );
  }

  // Update purchase log with new inventoryId
  updatePurchaseLog(): void {
    this.purchaseLogService.updatePurchaseLog(this.purchaseLog.userId, this.purchaseLog.inventoryId).subscribe(
      response => {
        this.successMessage = 'Purchase log updated successfully';
        this.errorMessage = '';
      },
      error => {
        this.errorMessage = 'Error updating purchase log';
        this.successMessage = '';
      }
    );
  }
}
