import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from "./home/home.component";
import {RouterOutlet} from "@angular/router";
import {AppRoutingModule} from "./app-routing.module";
import { RegisterComponent } from './register/register.component';
import { FooterComponent } from './footer/footer.component';
import {ClientProfileComponent} from "./client-profile/client-profile.component";
import {EmployeeProfileComponent} from "./employee-profile/employee-profile.component";
import {HttpClientModule} from "@angular/common/http";
import { OwnerInfoPanelComponent } from './owner-info-panel/owner-info-panel.component';
import {OwnerWorkerPanelComponent} from "./owner-worker-panel/owner-worker-panel.component";
import {FormsModule} from "@angular/forms";
import { OwnerMenuComponent } from './owner-menu/owner-menu.component';
import {CompanyAppointmentComponent} from "./company-appointment/company-appointment.component";
import {FullCalendarModule} from "@fullcalendar/angular";
import {NgxSliderModule} from "@angular-slider/ngx-slider";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    FooterComponent,
    ClientProfileComponent,
    EmployeeProfileComponent,
    OwnerInfoPanelComponent,
    OwnerWorkerPanelComponent,
    OwnerMenuComponent,
    CompanyAppointmentComponent
  ],
    imports: [
        BrowserModule,
        NgbModule,
        RouterOutlet,
        AppRoutingModule,
        HttpClientModule,
        FormsModule,
        FullCalendarModule,
        NgxSliderModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
