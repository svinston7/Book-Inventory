import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PermroleComponent } from './permrole.component';

describe('PermroleComponent', () => {
  let component: PermroleComponent;
  let fixture: ComponentFixture<PermroleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PermroleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PermroleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
