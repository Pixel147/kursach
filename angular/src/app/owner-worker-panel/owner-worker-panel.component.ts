import {Component} from '@angular/core';
import {Worker} from "../../assets/request/worker";
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'app-owner-worker-panel',
  templateUrl: './owner-worker-panel.component.html',
  styleUrls: ['./owner-worker-panel.component.css']
})
export class OwnerWorkerPanelComponent {
  constructor(private http: HttpClient) {
    this.getUsers();
  }

  worker: Worker | any;
  workers: Worker[] | any;
  username: string = '';
  email: string = '';
  phone: string = '';
  fullname: string = '';
  password: string = '';
  repeatPassword: string = '';

  dataValidation(): boolean {
    return this.username != '' && this.password == this.repeatPassword && this.phone != '' && this.fullname != '' && this.email != '';
  }

  createWorker() {
    if (this.dataValidation()) {
      const headers = new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem("token")}`
      })
      this.worker = new Worker(this.username, this.email, this.phone, this.fullname, this.password)
      this.http.post('http://localhost:8080/register/worker', this.worker, {headers: headers}).subscribe(
        {
          next: (data: any) => {
            console.log("added pidaras");
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
    this.http.get("http://localhost:8080/ownerWorkers",{headers: headers}).subscribe({
      next: (data: any) => {
        this.workers = data;
        console.log(data);
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
        console.log(data);
        this.getUsers();
      },
        error: (err: any) => {
        console.log(err);
      }
    });
  }
}

