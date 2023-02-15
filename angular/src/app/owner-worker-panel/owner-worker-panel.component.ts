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

  minValue: number = 0.0;
  maxValue: number = 23;
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
}







