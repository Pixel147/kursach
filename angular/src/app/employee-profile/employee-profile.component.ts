import {Component, ElementRef, ViewChild} from '@angular/core';
import * as bootstrap from 'bootstrap';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserInfo} from "../../assets/response-and-request/UserInfo";
import {UserAppointment} from "../../assets/response-and-request/UserAppointment";
import {MyEvent} from "../../assets/response-and-request/myEvent";
import {Calendar, CalendarOptions} from '@fullcalendar/core'
import timeGridPlugin from '@fullcalendar/timegrid'
import {FullCalendarComponent} from "@fullcalendar/angular";
import { DatePipe } from '@angular/common';
import {JobAppointments} from "../../assets/response-and-request/JobAppointments";
import {WorkerInfoResponse} from "../../assets/response-and-request/WorkerInfoResponse";

@Component({
  selector: 'app-employee-profile',
  templateUrl: './employee-profile.component.html',
  styleUrls: ['./employee-profile.component.css']
})
export class EmployeeProfileComponent {
  // @ts-ignore
  @ViewChild('calendar') calendarComponent: FullCalendarComponent;
  // @ts-ignore
  @ViewChild('exampleModal') exampleModal: ElementRef;
  constructor(private http: HttpClient) {

  }
  ngOnInit() {
    this.getWorkerInfo();
    this.getAppointments();
  }
  ngAfterViewInit(){
    const calendarApi = this.calendarComponent.getApi();
    const datePipe = new DatePipe('en-US');
    calendarApi.prev();
    calendarApi.next();
    const unformatedDate = new Date(calendarApi.getDate().toJSON());
    const formatedDate = datePipe.transform(unformatedDate, 'yyyy-MM-dd');
    this.getJobAppointments(formatedDate);
  }
  workerInfo:WorkerInfoResponse | any = new WorkerInfoResponse();
  workerAppointments:UserAppointment | any = new UserAppointment(
    0,'','','','','','');
  workerJobAppointments:JobAppointments | any = new JobAppointments(
    1,'','','','','','','');
  events:any[] = [];
  eventDisplayData:JobAppointments = new JobAppointments(
    1,'','','','','','','');
  appointmentsFlag: any;
  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': `Bearer ${localStorage.getItem("token")}`
  })
  notWorkingDays:number[] = [];
  calendarOptions: CalendarOptions = {
    plugins: [timeGridPlugin],
    initialView: 'timeGridWeek',
    firstDay: 1,
    height: 800,
    hiddenDays: this.notWorkingDays,
    slotMinTime: "00:00:00",
    slotMaxTime: "24:00:00",
    headerToolbar: {
      center: 'title',
      left:'',
      right:''
    },
    eventTimeFormat: {
      hour: '2-digit',
      minute: '2-digit'
    },
    eventClick: this.handleEventClick.bind(this)
  }
  handleEventClick(info: any) {
    this.findJobAppointmentById(parseInt(info.event.id));
    this.openModal();
  }
  openModal() {
    const modalRef = new bootstrap.Modal(this.exampleModal.nativeElement, {});
    modalRef.show();
  }
  findJobAppointmentById(id:number){
    for(let i = 0; i < this.workerJobAppointments.length; i++){
      if(this.workerJobAppointments[i].id == id){
        this.eventDisplayData = this.workerJobAppointments[i];
      }
    }
  }
  getWorkerInfo(){
    this.http.get("http://localhost:8080/worker",{headers: this.headers}).subscribe(
      (data:any) => {
        this.workerInfo = data;
        this.setNotWorkingDays();
        this.setCalendarTimeDuration();
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
  getJobAppointments(date:any){
    this.http.get(`http://localhost:8080/worker/company/appointment/${date}`,{headers:this.headers}).subscribe(
      (data:any) => {
        this.workerJobAppointments = data;
        this.getEventsFromJobAppointments(this.workerJobAppointments);
      },
      (error:any) =>{
        console.log(error);
      }
    )
  }
  cancelAppointment(event:any){
    const status = "canceled"
    this.http.put(`http://localhost:8080/worker/appointment/${event.target.id}`,{status},{headers:this.headers}).subscribe(
      (data:any) => {

      },
      (error:any) => {
        console.log(error);
      }
    )
    this.getAppointments();
  }
  getEventsFromJobAppointments(jobAppointments:any){
    let arr = [];
    for(let i = 0; i < jobAppointments.length; i++){
      let item:MyEvent = new MyEvent('1','1','1','1');
      item.id = jobAppointments[i].id.toString();
      item.title = jobAppointments[i].service;
      item.start = jobAppointments[i].dateStart + 'T' + jobAppointments[i].timeStart;
      item.end = jobAppointments[i].dateEnd + 'T' + jobAppointments[i].timeEnd;
      arr.push(item);
    }
    this.calendarOptions.events = arr;
  }
  prevWeek(){
    const calendarApi = this.calendarComponent.getApi();
    calendarApi.prev();
    const datePipe = new DatePipe('en-US');
    const unformatedDate = new Date(calendarApi.getDate().toJSON());
    const formatedDate = datePipe.transform(unformatedDate, 'yyyy-MM-dd');
    this.getJobAppointments(formatedDate);
  }
  nextWeek(){
    const calendarApi = this.calendarComponent.getApi();
    calendarApi.next();
    const datePipe = new DatePipe('en-US');
    const unformatedDate = new Date(calendarApi.getDate().toJSON());
    const formatedDate = datePipe.transform(unformatedDate, 'yyyy-MM-dd');
    this.getJobAppointments(formatedDate);
  }
  setNotWorkingDays(){
    this.notWorkingDays.push(this.workerInfo.mondayFlag);
    this.notWorkingDays.push(this.workerInfo.tuesdayFlag);
    this.notWorkingDays.push(this.workerInfo.wednesdayFlag);
    this.notWorkingDays.push(this.workerInfo.thursdayFlag);
    this.notWorkingDays.push(this.workerInfo.fridayFlag);
    this.notWorkingDays.push(this.workerInfo.saturdayFlag);
    this.notWorkingDays.push(this.workerInfo.sundayFlag);
    this.calendarOptions.hiddenDays = this.notWorkingDays;
  }
  setCalendarTimeDuration(){
    this.calendarOptions.slotMinTime = this.workerInfo.workStartsIn + ":00";
    this.calendarOptions.slotMaxTime = this.workerInfo.workEndsIn + ":00";
  }
}
