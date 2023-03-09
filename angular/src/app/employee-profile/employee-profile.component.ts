import { Component } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserInfo} from "../../assets/request/UserInfo";
import {UserAppointment} from "../../assets/request/UserAppointment";
import {Calendar, CalendarOptions} from '@fullcalendar/core'
import timeGridPlugin from '@fullcalendar/timegrid'

@Component({
  selector: 'app-employee-profile',
  templateUrl: './employee-profile.component.html',
  styleUrls: ['./employee-profile.component.css']
})
export class EmployeeProfileComponent {
  constructor(private http: HttpClient) {}
  ngOnInit() {
    this.getWorkerInfo();
    this.getAppointments();
  }
  workerInfo:UserInfo =  new UserInfo('','','','');
  workerAppointments:UserAppointment | any = new UserAppointment(
    0,'','','','','',''
  );
  appointmentsFlag: any;
  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${localStorage.getItem("token")}`
  })
  calendarOptions: CalendarOptions = {
    plugins: [timeGridPlugin],
    initialView: 'timeGridWeek',
    firstDay: 1,
    height: 800,
    headerToolbar: {
      left: 'prev,next',
      center: 'title',
      right: 'timeGridWeek,timeGridDay'
    },
    eventTimeFormat: {
      hour: '2-digit',
      minute: '2-digit',
      meridiem: false
    },
    events: [
      {
        id:'1',
        title: 'simple event',
        description:'text',
        start: '2023-03-08T15:00'
      }
    ],
    eventClick: function(info) {
      console.log('Event: ' + info.event.title);
      console.log(info.event.start);
      console.log(info.event.id);
    }
  }
  getWorkerInfo(){
    this.http.get("http://localhost:8080/worker",{headers: this.headers}).subscribe(
      (data:any) => {
        this.workerInfo = data;
      }
    )
  }
  getAppointments(){
    this.http.get("http://localhost:8080/worker/appointment",{headers: this.headers}).subscribe(
      (data:any) => {
        this.workerAppointments = data;
        this.appointmentsFlag = this.workerAppointments != null;
      }
    )
  }
}
