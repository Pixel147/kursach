import {Component} from '@angular/core';
import {Worker} from "../../assets/request/worker";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { Options } from '@angular-slider/ngx-slider';

@Component({
  selector: 'app-owner-worker-panel',
  templateUrl: './owner-worker-panel.component.html',
  styleUrls: ['./owner-worker-panel.component.css']
})

export class OwnerWorkerPanelComponent {

  minValueMon: number = 0;
  maxValueMon: number = 23;
  minValueTue: number = 0;
  maxValueTue: number = 23;
  minValueWed: number = 0;
  maxValueWed: number = 23;
  minValueThu: number = 0;
  maxValueThu: number = 23;
  minValueFri: number = 0;
  maxValueFri: number = 23;
  minValueSat: number = 0;
  maxValueSat: number = 23;
  minValueSun: number = 0;
  maxValueSun: number = 23;
  monFlag = true;
  tueFlag = false;
  wedFlag = false;
  thuFlag = false
  friFlag = false;
  satFlag = false;
  sunFlag = false;
  options: Options = {
    floor: 0,
    ceil: 23,
    showTicksValues: true

}
  constructor(private http: HttpClient) {
    this.getUsers();
  }
  repeatPassword:string = '';
  worker: Worker = new Worker('','','','','', '');
  workers: Worker[] | any;

  dataValidation(): boolean {
    return this.worker.username != '' &&
      this.worker.password == this.repeatPassword &&
      this.worker.phone != '' &&
      this.worker.fullname != '' &&
      this.worker.email != '';
  }

  createWorker() {
    if (this.dataValidation()) {
      const headers = new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem("token")}`
      })
      this.http.post('http://localhost:8080/register/worker', this.worker, {headers: headers}).subscribe(
        {
          next: (data: any) => {
            this.getUsers();
          },
          error: (err: any) => {
            console.log(err);
          }
        }
      )
    } else {
      console.log("invalid data");
    }
  }

  getUsers(){
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem("token")}`
    })
    this.http.get(`http://localhost:8080/owner/${localStorage.getItem("id")}/workers`,{headers: headers}).subscribe({
      next: (data: any) => {
        this.workers = data;
      },
      error: (err: any) => {
        console.log(err);
      }
    });
  }

  deleteWorker($event:any){
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem("token")}`
    })
    const id = $event.target.id;
    this.http.delete(`http://localhost:8080/worker${id}`,{headers: headers}).subscribe({
      next: (data: any) => {
        this.getUsers();
      },
        error: (err: any) => {
        console.log(err);
      }
    });
  }

  MonFlag()
  {
    this.monFlag = true;
    this.tueFlag = this.wedFlag = this.thuFlag = this.friFlag = this.satFlag = this.sunFlag = false;
  }

  TueFlag()
  {
    this.tueFlag = true;
    this.monFlag = this.wedFlag = this.thuFlag = this.friFlag = this.satFlag = this.sunFlag = false;
  }

  WedFlag()
  {
    this.wedFlag = true;
    this.monFlag = this.tueFlag = this.thuFlag = this.friFlag = this.satFlag = this.sunFlag = false;
  }

  ThuFlag()
  {
    this.thuFlag = true;
    this.monFlag = this.wedFlag = this.tueFlag = this.friFlag = this.satFlag = this.sunFlag = false;
  }

  FriFlag()
  {
    this.friFlag = true;
    this.monFlag = this.wedFlag = this.thuFlag = this.tueFlag = this.satFlag = this.sunFlag = false;
  }

  SatFlag()
  {
    this.satFlag = true;
    this.monFlag = this.wedFlag = this.thuFlag = this.friFlag = this.tueFlag = this.sunFlag = false;
  }

  SunFlag()
  {
    this.sunFlag = true;
    this.monFlag = this.wedFlag = this.thuFlag = this.friFlag = this.satFlag = this.tueFlag = false;
  }
}







