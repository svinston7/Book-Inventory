import { Component, OnInit } from '@angular/core';
import { Publisher } from '../../../model/Publisher';
import { GetPublisherService } from '../../../service/get-publisher.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin-publisher',
  imports:[CommonModule,FormsModule],
  templateUrl: './admin-publisher.component.html',
  styleUrls: ['./admin-publisher.component.css']
})
export class AdminPublisherComponent implements OnInit {

  publishers: Publisher[] = [];
  selectedPublisherId: number | null = null;
  selectedField: string = '';
  updateValue: string = '';
  newPublisher: Publisher = { publisherId: 0, name: '', city: '', stateCode: '' };
  showAddPublisherForm: boolean = false;
  showValueInputBox: boolean = false;
  popupMessage: string = '';
isPopupVisible: boolean = false;


  constructor(private publisherService: GetPublisherService) {}

  ngOnInit(): void {
    this.refreshPublisherList();
  }

  // Get all publishers
  refreshPublisherList() {
    this.publisherService.showPublishers().subscribe(
      response => {
        this.publishers = response;
      },
      error => {
        alert('Error loading publishers: ' + error);
      }
    );
  }

  // Update Publisher details based on selected field
  updatePublisher(publisherId: number) {
    if (!this.selectedField || !this.updateValue) {
      alert('Please select a field and enter a new value.');
      return;
    }

    switch (this.selectedField) {
      case 'name':
        this.publisherService.updatePublisherName(publisherId, this.updateValue).subscribe(
          response => {
            this.popupMessage='Publisher Name updated successfully!';
            this.isPopupVisible=true;
            this.resetUpdateFields();
            this.refreshPublisherList();
          },
          error => {
            alert('Error updating Publisher Name: ' + error);
          }
        );
        break;
      case 'city':
        this.publisherService.updatePublisherCity(publisherId, this.updateValue).subscribe(
          response => {
            this.popupMessage = 'Publisher City updated successfully!';
            this.isPopupVisible = true; 
            this.resetUpdateFields();
            this.refreshPublisherList();
          },
          error => {
            alert('Error updating Publisher City: ' + error);
          }
        );
        break;
      case 'stateCode':
        this.publisherService.updatePublisherState(publisherId, this.updateValue).subscribe(
          response => {
            this.popupMessage = 'Publisher State updated successfully!';
          this.isPopupVisible = true;
            this.resetUpdateFields();
            this.refreshPublisherList();
          },
          error => {
            alert('Error updating Publisher State: ' + error);
          }
        );
        break;
      default:
        alert('Invalid field selected.');
    }
  }

  // Reset the update fields after updating a publisher
  resetUpdateFields() {
    this.selectedField = '';
    this.updateValue = '';
  }

 // Add a new publisher
  addPublisher() {
    this.publisherService.addPublisher(this.newPublisher).subscribe(
      response => {
       // alert('Publisher added successfully!');
       this.popupMessage = 'Publisher added successfully!';
       this.isPopupVisible = true;
        this.resetAddPublisherForm();
        this.refreshPublisherList();
      },
      error => {
        alert('Error adding publisher: ' + error);
        
      }
    );
  }

  // Reset the add publisher form
  resetAddPublisherForm() {
    this.newPublisher = { publisherId: 0, name: '', city: '', stateCode: '' };
    this.showAddPublisherForm = false;
  }

  // Toggle the Add Publisher form visibility
  toggleAddPublisherForm() {
    this.showAddPublisherForm = !this.showAddPublisherForm;
  }
  toggleEditForm(publisherId: number) {
    if (this.selectedPublisherId === publisherId) {
      this.selectedPublisherId = null;  // Close form if already open
      this.showValueInputBox = false;
    } else {
      this.selectedPublisherId = publisherId;
      this.selectedField = '';  // Reset field selection
      this.updateValue = '';    // Reset value
      this.showValueInputBox = false; 
    }
  }
  showValueInput(publisherId: number) {
    if (this.selectedField) {
      this.showValueInputBox = true;
    } else {
      this.showValueInputBox = false;
    }
  }

  cancelEdit() {
    this.selectedPublisherId = null;
    this.showValueInputBox = false;
  }
  
}
