import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {HomeComponent} from "./home/home.component";
import {RegisterComponent} from "./register/register.component";
import {ClientProfileComponent} from "./client-profile/client-profile.component";
import {EmployeeProfileComponent} from "./employee-profile/employee-profile.component";

const routes: Routes = [
  {path: '', component:HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component:RegisterComponent},
  {path: 'client', component:ClientProfileComponent},
  {path: 'employee', component:EmployeeProfileComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
