import { Component } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';


@Component({
  selector: 'app-company-appointment',
  templateUrl: './company-appointment.component.html',
  styleUrls: ['./company-appointment.component.css']
})
export class CompanyAppointmentComponent {

  constructor() {

  }
  public calendarOptions: CalendarOptions = {
    plugins: [dayGridPlugin],
    initialView: 'dayGridMonth',
    height: 500
  };
  handleDateClick(arg:any) {
    console.log(arg.date);
  }


}
