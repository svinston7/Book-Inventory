import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookShowcaswComponent } from './book-showcase.component';

describe('BookShowcaswComponent', () => {
  let component: BookShowcaswComponent;
  let fixture: ComponentFixture<BookShowcaswComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BookShowcaswComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookShowcaswComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
