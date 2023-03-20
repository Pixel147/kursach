import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerScheduleComponent } from './owner-schedule.component';

describe('OwnerScheduleComponent', () => {
  let component: OwnerScheduleComponent;
  let fixture: ComponentFixture<OwnerScheduleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OwnerScheduleComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OwnerScheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
