import {Component, ViewChild} from '@angular/core';
import {CalendarOptions} from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';
import {ActivatedRoute} from "@angular/router";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CompanyAppointment} from "../../assets/request/companyAppointment";
import {FullCalendarComponent} from "@fullcalendar/angular";
import {AppointmentRegisterRequest} from "../../assets/request/AppointmentRegisterRequest";

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.css']
})
export class CompanyComponent {
  // @ts-ignore
  @ViewChild('calendar') calendarComponent: FullCalendarComponent ;

  constructor(private route: ActivatedRoute, private http: HttpClient) {
  }
  ngOnInit() {
    this.route.params.subscribe((params) => {
      const id = params['id'];
      this.id = id;
      this.http.get(`http://localhost:8080/company/${id}`).subscribe(
        (data: any) => {
          this.companyData = new CompanyAppointment(data.name);
        }
      );
      this.getWorkerAndServices();
    });
  }

  id:any;
  selectedWorkerId:any;
  workers:any;
  // @ts-ignore
  selectedWorker:Appointment = {
    fullname:'',
    service:'',
    date:'',
    time:''
  };
  workDays:any;
  workerFreeTime:any;
  dayPickedCheck: boolean = false;
  registerAppData:AppointmentRegisterRequest = new AppointmentRegisterRequest('',0,'');

  public companyData = new CompanyAppointment("");
  private notWorkingDays:number[] = [];
  calendarOptions: CalendarOptions = {
    initialView: 'dayGridMonth',
    plugins: [
      interactionPlugin,
      dayGridPlugin,
      timeGridPlugin,
      listPlugin,
    ],
    firstDay: 1,
    hiddenDays: this.notWorkingDays,
    height: 500,
    dateClick: this.handleDateClick.bind(this)//todo in owner schedule
  };
  handleDateClick(arg: any) {
    this.registerAppData.setDate(arg.dateStr);
    this.selectedWorker.date = arg.dateStr;
    this.http.get(`http://localhost:8080/worker/${this.selectedWorkerId}/day/${arg.dateStr}`).subscribe(
      (data:any) =>{
        this.workerFreeTime = data;
      }
    );
  }
  selectTime(event:any){
    this.registerAppData.setTime(event.target.innerHTML);
    this.selectedWorker.time = event.target.innerHTML;
  }
  getWorkerAndServices(){
    this.http.get(`http://localhost:8080/company/${this.id}/workers`).subscribe(
      (data:any) =>{
        this.workers = data;
      }
    )
  }
  getWorkDaysWorker(event: any){
    const workerId = event.target.id;
    this.selectedWorkerId = event.target.id;
    this.setSelectedWorkerById(event.target.id);
    this.registerAppData.setWorkerId(event.target.id);
    this.http.get(`http://localhost:8080/worker/${workerId}/workdays`).subscribe(
      (data:any)=>{
        this.workDays = data;
        this.setNotWorkingDays();
        if(this.dayPickedCheck)
        {
          this.dayPickedCheck = false;
        }
        else
        {
          this.dayPickedCheck = true;
        }
      }
    );
  }
  setSelectedWorkerById(id:number){
    for(let i = 0; i < this.workers.length; i++){
      if(this.workers[i].id == id){
        this.selectedWorker.fullname = this.workers[i].fullname;
        this.selectedWorker.service = this.workers[i].service;
      }
    }
  }
  setNotWorkingDays(){
    this.notWorkingDays = [];
    if(this.workDays.monday == "false"){
      this.notWorkingDays.push(1);
    }
    if(this.workDays.tuesday == false){
      this.notWorkingDays.push(2);
    }
    if(this.workDays.wednesday == false){
      this.notWorkingDays.push(3);
    }
    if(this.workDays.thursday == false){
      this.notWorkingDays.push(4);
    }
    if(this.workDays.friday == false){
      this.notWorkingDays.push(5);
    }
    if(this.workDays.saturday == false){
      this.notWorkingDays.push(6);
    }
    if(this.workDays.sunday == false){
      this.notWorkingDays.push(0);
    }
    this.updateCalendar()
  }
  updateCalendar(){
    // @ts-ignore
    const calendarApi = this.calendarComponent.getApi();
    calendarApi.destroy();
    calendarApi.setOption('hiddenDays',this.notWorkingDays);
    calendarApi.render();
  }
  createAppointment(){
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem("token")}`
    })
    this.http.post("http://localhost:8080/register/appointment",this.registerAppData,{headers: headers}).subscribe(
      (data:any) =>{
        console.log(data);
      }
    );
  }
}
