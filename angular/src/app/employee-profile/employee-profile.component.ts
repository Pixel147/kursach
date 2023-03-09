import {Component, ViewChild} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserInfo} from "../../assets/request/UserInfo";
import {UserAppointment} from "../../assets/request/UserAppointment";
import {Calendar, CalendarOptions} from '@fullcalendar/core'
import timeGridPlugin from '@fullcalendar/timegrid'
import {FullCalendarComponent} from "@fullcalendar/angular";
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-employee-profile',
  templateUrl: './employee-profile.component.html',
  styleUrls: ['./employee-profile.component.css']
})
export class EmployeeProfileComponent {
  // @ts-ignore
  @ViewChild('calendar') calendarComponent: FullCalendarComponent;
  constructor(private http: HttpClient) {

  }
  workerInfo:UserInfo  | any =  new UserInfo('','','','');
  workerAppointments:UserAppointment | any = new UserAppointment(
    0,'','','','','',''
  );
  ngOnInit() {
    this.getWorkerInfo();
    this.getAppointments();
  }
  ngAfterViewInit(){
    const calendarApi = this.calendarComponent.getApi();
    const datePipe = new DatePipe('en-US');
    const unformatedDate = new Date(calendarApi.getDate().toJSON());
    const formatedDate = datePipe.transform(unformatedDate, 'yyyy-MM-dd');
  }
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
      center: 'title',
      left:'',
      right:''
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
        start: '2023-03-10T15:00'
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
  prevWeek(){
    const calendarApi = this.calendarComponent.getApi();
    calendarApi.prev();
    const datePipe = new DatePipe('en-US');
    const unformatedDate = new Date(calendarApi.getDate().toJSON());
    const formatedDate = datePipe.transform(unformatedDate, 'yyyy-MM-dd');
    console.log(formatedDate);
  }
  nextWeek(){
    const calendarApi = this.calendarComponent.getApi();
    calendarApi.next();
    const datePipe = new DatePipe('en-US');
    const unformatedDate = new Date(calendarApi.getDate().toJSON());
    const formatedDate = datePipe.transform(unformatedDate, 'yyyy-MM-dd');
    console.log(formatedDate);
  }
}
