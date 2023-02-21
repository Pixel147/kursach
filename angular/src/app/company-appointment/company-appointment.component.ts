import {Component} from '@angular/core';
import {CalendarOptions} from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {CompanyAppointment} from "../../assets/request/companyAppointment";

@Component({
  selector: 'app-company-appointment',
  templateUrl: './company-appointment.component.html',
  styleUrls: ['./company-appointment.component.css']
})
export class CompanyAppointmentComponent {

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

  public companyData = new CompanyAppointment("");
  private notWorkingDays = [6, 0];
  id:any;
  workerAndService:any;
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
}
