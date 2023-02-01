import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyAppointmentComponent } from './company-appointment.component';

describe('CompanyAppointmentComponent', () => {
  let component: CompanyAppointmentComponent;
  let fixture: ComponentFixture<CompanyAppointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CompanyAppointmentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CompanyAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
