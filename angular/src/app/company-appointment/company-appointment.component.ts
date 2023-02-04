import { Component } from '@angular/core';

@Component({
  selector: 'app-company-appointment',
  templateUrl: './company-appointment.component.html',
  styleUrls: ['./company-appointment.component.css']
})
export class CompanyAppointmentComponent {

  constructor() {
    this.now = new Date();
    console.log(this.now);
    this.year = (new Date()).getFullYear();
    this.month = (new Date()).getMonth();
    this.day = (new Date()).getDay();
    this.day = this.day - 2;

    if(this.month == 1) {this.month = "February";}
    else if(this.month == 2) {this.month = "March";}
    else if(this.month == 3) {this.month = "April";}
    else if(this.month == 4) {this.month = "May";}
    else if(this.month == 5) {this.month = "June";}
    else if(this.month == 6) {this.month = "July";}
    else if(this.month == 7) {this.month = "August";}
    else if(this.month == 8) {this.month = "September";}
    else if(this.month == 9) {this.month = "October";}
    else if(this.month == 10) {this.month = "November";}
    else if(this.month == 11) {this.month = "December";}
    else{this.month = "January";}
  }
  public now: Date = new Date();

  year:any;
  month:any;
  day:any;
  curDayFlag = false;


   getTime()
   {
     this.now = new Date();
     console.log(this.now)
  }

}
