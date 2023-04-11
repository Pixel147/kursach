import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OwnerWorkerPanelComponent } from './owner-worker-panel.component';

describe('OwnerWorkerPanelComponent', () => {
  let component: OwnerWorkerPanelComponent;
  let fixture: ComponentFixture<OwnerWorkerPanelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OwnerWorkerPanelComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OwnerWorkerPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
