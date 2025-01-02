import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPublisherComponent } from './admin-publisher.component';

describe('AdminPublisherComponent', () => {
  let component: AdminPublisherComponent;
  let fixture: ComponentFixture<AdminPublisherComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminPublisherComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminPublisherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
