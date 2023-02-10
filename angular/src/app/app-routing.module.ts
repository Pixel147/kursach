import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";
import {RegisterComponent} from "./register/register.component";
import {ClientProfileComponent} from "./client-profile/client-profile.component";
import {EmployeeProfileComponent} from "./employee-profile/employee-profile.component";
import {OwnerInfoPanelComponent} from "./owner-info-panel/owner-info-panel.component";
import {OwnerWorkerPanelComponent} from "./owner-worker-panel/owner-worker-panel.component";
import {CompanyAppointmentComponent} from "./company-appointment/company-appointment.component";
import {AuthGuard} from "./auth/auth.guard";


const routes: Routes = [
  {path: '', component:HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component:RegisterComponent},
  {path: 'client', component:ClientProfileComponent, canActivate:[AuthGuard]},
  {path: 'employee', component:EmployeeProfileComponent, canActivate:[AuthGuard]},
  {path: 'ownerInfo', component:OwnerInfoPanelComponent, canActivate:[AuthGuard]},
  {path: 'ownerWorkers', component:OwnerWorkerPanelComponent, canActivate:[AuthGuard]},
  {path: 'company/:nameCompany', component:CompanyAppointmentComponent},
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
