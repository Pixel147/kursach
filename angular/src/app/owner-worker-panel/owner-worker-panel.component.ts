import { Component } from '@angular/core';
import { Worker} from "../../assets/request/worker";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'app-owner-worker-panel',
  templateUrl: './owner-worker-panel.component.html',
  styleUrls: ['./owner-worker-panel.component.css']
})
export class OwnerWorkerPanelComponent {
  constructor(private http: HttpClient) {
  }
  worker:Worker | any;
  token:any;
  username:string = '';
  email:string= '';
  phone:string= '';
  fullname:string= '';
  password:string= '';
  repeatPassword:string = '';
  dataValidation():boolean{
    console.log("username :", this.username);
    console.log("email :", this.email);
    console.log("fullname :", this.fullname);
    console.log("phone :", this.phone);
    console.log("password :", this.password);
    console.log("repeatPassword :", this.repeatPassword);
    return this.username != '' && this.password == this.repeatPassword && this.phone != '' && this.fullname != '' && this.email != '';
  }
  createWorker(){
    if(this.dataValidation()){
      this.token = localStorage.getItem('token');
      const headers = new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      })
      this.worker = new Worker(this.username,this.email,this.phone,this.fullname,this.password)
      this.http.post('http://localhost:8080/register/worker',this.worker,{headers: headers}).subscribe({
        next:(data: any) => {
          console.log("added pidaras");
        },
        error:(err:any) =>{
          console.log(err);
        }
      }

    )
    }
    else{
      console.log("invalid data");
    }
  }
}

