import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';

import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { publishBook } from '../../service/publishbook.service';
import { HomeComponent } from '../home/home.component';

@Component({
  selector: 'app-publisher',
  templateUrl: './publisher.component.html',
  styleUrls: ['./publisher.component.css'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    HomeComponent,
  ],
})
export class PublisherComponent {
  isPopupVisible = false; 
  popupMessage = ''; 
  constructor(private publishbook: publishBook) {}

  /**
   * Displays a popup message and hides it after 4 seconds.
   * @param message - The message to display in the popup
   */
  private showPopup(message: string): void {
    this.popupMessage = message;
    this.isPopupVisible = true;

    setTimeout(() => {
      this.isPopupVisible = false;
    }, 4000);
  }

  /**
   * Handles form submission
   * @param publishForm - The submitted form instance
   */
  onSubmit(publishForm: NgForm): void {
    if (publishForm.valid) {
      const formData = {
        publisherName: publishForm.value.publisherName,
        publisherId: publishForm.value.publisherId,
        email: publishForm.value.email,
        title: publishForm.value.title,
        description: publishForm.value.bookDescription,
        categoryId: publishForm.value.category,
        edition: publishForm.value.edition,
        isbn: publishForm.value.isbn,
        image: publishForm.value.image,
      };

      this.publishbook.publishBook(formData).subscribe(
        (response) => {
          console.log('Book Data Submitted:', response);
          this.showPopup('🎉 Success! Your book has been published!');

          publishForm.reset();
        },
        (error) => {
          console.error('Error submitting form:', error);
          this.showPopup('There was an error submitting the form. Please try again.');
        }
      );
    } else {
      alert('Please fill out all required fields correctly.');
    }
  }
}
