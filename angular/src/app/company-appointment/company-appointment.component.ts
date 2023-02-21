import {Component, ViewChild} from '@angular/core';
import {CalendarOptions} from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {CompanyAppointment} from "../../assets/request/companyAppointment";
import {FullCalendarComponent} from "@fullcalendar/angular";
import {ca} from "@fullcalendar/core/internal-common";

@Component({
  selector: 'app-company-appointment',
  templateUrl: './company-appointment.component.html',
  styleUrls: ['./company-appointment.component.css']
})
export class CompanyAppointmentComponent {
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
  workerAndService:any;
  workDays:any;

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
    dateClick: this.handleDateClick.bind(this)
  };
  handleDateClick(arg: any) {
    console.log('date click! ', arg.dateStr)
  }
  getWorkerAndServices(){
    this.http.get(`http://localhost:8080/company/${this.id}/services`).subscribe(
      (data:any) =>{
        this.workerAndService = data;
      }
    )
  }
  getWorkDaysWorker(event: any){
    const workerId = event.target.id;
    this.http.get(`http://localhost:8080/worker/${workerId}/workdays`).subscribe(
      (data:any)=>{
        this.workDays = data;
        this.setNotWorkingDays(this.workDays);
      }
    );
  }
  setNotWorkingDays(workDays:any){
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
}
