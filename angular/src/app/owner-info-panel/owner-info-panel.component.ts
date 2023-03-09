import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";
import {ownerInfo} from "../../assets/request/ownerInfo";
import {Worker} from "../../assets/request/worker";



@Component({
  selector: 'app-owner-info-panel',
  templateUrl: './owner-info-panel.component.html',
  styleUrls: ['./owner-info-panel.component.css']
})
export class OwnerInfoPanelComponent {
  constructor(private http: HttpClient) {
    this.getUsers();
    this.loadOwnerInfo();
    this.getAppointments();
  }
  ownerInfo:ownerInfo = new ownerInfo("","","",'','','');
  token: any;
  descriptionEditingFlag = false;
  textAreaDescriptionText = '';
  worker: Worker = new Worker('','','','','', '',
    0,23,0,23,0,23,0,
    23,0,23,0,23,0,23);
  workers: Worker[] | any;
  // @ts-ignore
  appointment: Appointment[] | any;
  clickedOnEdit():void{
    this.textAreaDescriptionText = this.ownerInfo.description;
    this.descriptionEditingFlag = true;
  }
  updateDescription():void{
    this.token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.token}`
    })
    this.http.put(`http://localhost:8080/owner/${localStorage.getItem("id")}/description`,this.textAreaDescriptionText,{headers: headers}).subscribe({
      next:(data:any) => {
        console.log(data);
      },
      error:(err:any) =>{
        console.log(err)
      }
    })
    this.ownerInfo.description = this.textAreaDescriptionText;
    this.descriptionEditingFlag = false;
  }
  loadOwnerInfo():void{
      this.token = localStorage.getItem('token');
      const headers = new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      })
      this.http.get(`http://localhost:8080/owner/${localStorage.getItem("id")}`,{headers: headers}).subscribe(
        {
          next:(data:any) => {
            this.ownerInfo = new ownerInfo(data.phone, data.fullname, data.companyName, data.location, data.description, data.username)
          },
          error:(err:any) =>{
            console.log(err);
          }
        },
      );
  }
  getUsers(){
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem("token")}`
    })
    this.http.get(`http://localhost:8080/owner/${localStorage.getItem("id")}/workers`,{headers: headers}).subscribe({
      next: (data: any) => {
        this.workers = data;
      },
      error: (err: any) => {
        console.log(err);
      }
    });
  }

  getAppointments()
  {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem("token")}`
    })
    this.http.get(`http://localhost:8080/owner/appointments`,{headers: headers}).subscribe({
      next: (data: any) => {
        console.log(data);
        this.appointment = data;
      },
      error: (err: any) => {
        console.log(err);
      }
    });
  }
}
