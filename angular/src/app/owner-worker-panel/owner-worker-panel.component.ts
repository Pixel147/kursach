import {Component} from '@angular/core';
import {Worker} from "../../assets/response-and-request/worker";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { Options } from '@angular-slider/ngx-slider';

@Component({
  selector: 'app-owner-worker-panel',
  templateUrl: './owner-worker-panel.component.html',
  styleUrls: ['./owner-worker-panel.component.css']
})

export class OwnerWorkerPanelComponent {

  monSwitch: boolean = true; //is day off or on
  tueSwitch: boolean = true;
  wedSwitch: boolean = true;
  thuSwitch: boolean = true;
  friSwitch: boolean = true;
  satSwitch: boolean = false;
  sunSwitch: boolean = false;

  monFlag = true; //flag for choose needed slider
  tueFlag = false;
  wedFlag = false;
  thuFlag = false
  friFlag = false;
  satFlag = false;
  sunFlag = false;

  options: Options = {
    floor: 0,
    ceil: 23,
    step: 1
  };

  constructor(private http: HttpClient) {
    this.getUsers();
  }
  repeatPassword:string = '';
  worker: Worker = new Worker('','','','','', '',
    0,23,0,23,0,23,0,
    23,0,23,0,23,0,23);
  workers: Worker[] | any;

  dataValidation(): boolean {
    return this.worker.username != '' &&
      this.worker.password == this.repeatPassword &&
      this.worker.phone != '' &&
      this.worker.fullname != '' &&
      this.worker.email != '';
  }

  dayValidation():boolean
  {
    if(!this.monSwitch)
    {
      this.worker.mondayStart = -1;
      this.worker.mondayEnd = -1;
    }
    if(!this.tueSwitch)
    {
      this.worker.tuesdayStart = -1;
      this.worker.tuesdayEnd = -1;
    }
    if(!this.wedSwitch)
    {
      this.worker.wednesdayStart = -1;
      this.worker.wednesdayEnd = -1;
    }
    if(!this.thuSwitch)
    {
      this.worker.thursdayStart = -1;
      this.worker.thursdayEnd = -1;
    }
    if(!this.friSwitch)
    {
      this.worker.fridayStart = -1;
      this.worker.fridayEnd = -1;
    }
    if(!this.satSwitch)
    {
      this.worker.saturdayStart = -1;
      this.worker.saturdayEnd = -1;
    }
    if(!this.sunSwitch)
    {
      this.worker.sundayStart = -1;
      this.worker.sundayEnd = -1;
    }

    return true;
  }

  createWorker() {
    if (this.dataValidation() && this.dayValidation()) {
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



  monSwitchCheck()
  {
    if(this.monSwitch)
    {
      this.monSwitch = false;
    }
    else
    {
      this.monSwitch = true;
    }
  }

  tueSwitchCheck()
  {
    if(this.tueSwitch)
    {
      this.tueSwitch = false;
    }
    else
    {
      this.tueSwitch = true;
    }
  }

  wedSwitchCheck()
  {
    if(this.wedSwitch)
    {
      this.wedSwitch = false;
    }
    else
    {
      this.wedSwitch = true;
    }
  }

  thuSwitchCheck()
  {
    if(this.thuSwitch)
    {
      this.thuSwitch = false;
    }
    else
    {
      this.thuSwitch = true;
    }
  }

  friSwitchCheck()
  {
    if(this.friSwitch)
    {
      this.friSwitch = false;
    }
    else
    {
      this.friSwitch = true;
    }
  }

  satSwitchCheck()
  {
    if(this.satSwitch)
    {
      this.satSwitch = false;
    }
    else
    {
      this.satSwitch = true;
    }
  }

  sunSwitchCheck()
  {
    if(this.sunSwitch)
    {
      this.sunSwitch = false;
    }
    else
    {
      this.sunSwitch = true;
    }
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







