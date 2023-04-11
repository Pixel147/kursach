import { Component } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserInfo} from "../../assets/response-and-request/UserInfo";
import {UserAppointment} from "../../assets/response-and-request/UserAppointment";

@Component({
  selector: 'app-client-profile',
  templateUrl: './client-profile.component.html',
  styleUrls: ['./client-profile.component.css']
})
export class ClientProfileComponent {
  constructor(private http: HttpClient) {
  }
  ngOnInit() {
    this.getClientAppointments();
    this.getClientInfo();
  }

  clientData:UserInfo = new UserInfo('','','','') ;
  clientAppointments:UserAppointment | any = null;
  appointmentsFlag: any;
  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${localStorage.getItem("token")}`
  })

  getClientInfo(){
    this.http.get("http://localhost:8080/client",{headers: this.headers}).subscribe(
      (data:any) => {
        this.clientData = data;
      }
    );
  }
  getClientAppointments(){
    this.http.get("http://localhost:8080/client/appointment",{headers: this.headers}).subscribe(
      (data:any) => {
        this.clientAppointments = data;
        this.appointmentsFlag = this.clientAppointments != null;
      }
    )
  }
  deleteAppointment(event:any){
    this.http.delete(`http://localhost:8080/client/appointment/delete/${event.target.id}`,{headers:this.headers}).subscribe();
    this.getClientAppointments();
  }
}
