import { Component } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ActivatedRoute} from "@angular/router";
import {ClientInfo} from "../../assets/request/ClientInfo";
import {ClientAppointment} from "../../assets/request/ClientAppointment";

@Component({
  selector: 'app-client-profile',
  templateUrl: './client-profile.component.html',
  styleUrls: ['./client-profile.component.css']
})
export class ClientProfileComponent {
  constructor(private route: ActivatedRoute, private http: HttpClient) {
  }
  ngOnInit() {
    this.getClientAppointments();
    this.getClientInfo();
  }

  clientData:ClientInfo = new ClientInfo('','','','') ;
  clientAppointments:ClientAppointment | any = null;
  appointmentsFlag: boolean | undefined;
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
