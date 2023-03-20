import {Component, ViewChild} from '@angular/core';
import {FullCalendarComponent} from "@fullcalendar/angular";
import {ActivatedRoute} from "@angular/router";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CalendarOptions} from "@fullcalendar/core";
import interactionPlugin from "@fullcalendar/interaction";
import dayGridPlugin from "@fullcalendar/daygrid";
import timeGridPlugin from "@fullcalendar/timegrid";
import listPlugin from "@fullcalendar/list";

@Component({
  selector: 'app-owner-schedule',
  templateUrl: './owner-schedule.component.html',
  styleUrls: ['./owner-schedule.component.css']
})
export class OwnerScheduleComponent {
  constructor(private route: ActivatedRoute, private http: HttpClient) {
  }

  // @ts-ignore
  @ViewChild('calendar') calendarComponent: FullCalendarComponent;
  id:any;
  // @ts-ignore

  appointment: Appointment[] | any;

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
    dateClick: this.handleDateClick.bind(this),

    eventClick: function(info) {
      console.log('Event: ' + info.event.title);
      console.log(info.event.start);
      console.log(info.event.id);
    }
  };

  handleDateClick(arg: any) {
    this.getAppointmentsByDay(arg.dateStr);
  }
  getAppointmentsByDay(date:any)
  {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem("token")}`
    })
    this.http.get(`http://localhost:8080/owner/appointments/day/${date}`,{headers: headers}).subscribe({
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
