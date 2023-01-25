import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerInfoPanelComponent } from './owner-info-panel.component';

describe('OwnerInfoPanelComponent', () => {
  let component: OwnerInfoPanelComponent;
  let fixture: ComponentFixture<OwnerInfoPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OwnerInfoPanelComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OwnerInfoPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
