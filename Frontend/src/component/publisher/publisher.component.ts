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
    HttpClientModule,
    HomeComponent,
    FormsModule
  ],
})
export class PublisherComponent {
  isPopupVisible = false; // Controls the visibility of the popup notification
  popupMessage = ''; // Message to display in the popup

  constructor(private publishbook: publishBook) {}

  /**
   * Displays a popup notification and hides it after 4 seconds.
   * @param message - The notification message
   */
  private showPopup(message: string): void {
    this.popupMessage = message;
    this.isPopupVisible = true;

    setTimeout(() => {
      this.isPopupVisible = false;
    }, 4000);
  }

  /**
   * Handles form submission.
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
        price: publishForm.value.price.toString(), 
        edition: publishForm.value.edition,
        isbn: publishForm.value.isbn,
        image: publishForm.value.image,
      };

      console.log('Form data sent to backend:', formData);

      this.publishbook.publishBook(formData).subscribe(
        (response) => {
          console.log('Book data successfully submitted:', response);
          this.showPopup('🎉 Success! Your book has been published!');

          publishForm.reset(); // Reset the form after successful submission
        },
        (error) => {
          console.error('Error submitting the form:', error);
          this.showPopup('There was an error submitting the form. Please try again.');
        }
      );
    } else {
      alert('Please fill out all required fields correctly.');
    }
  }
}
