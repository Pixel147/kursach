export class Worker{
  username:string;
  email:string;
  phone:string;
  fullname:string;
  password:string;
  role:string;
  service:string;
  monStart: number;
  monEnd:number;
  tueStart: number;
  tueEnd:number;
  wedStart: number;
  wedEnd:number;
  thuStart: number;
  thuEnd:number;
  friStart: number;
  friEnd:number;
  satStart: number;
  satEnd:number;
  sunStart: number;
  sunEnd:number;


  constructor(name: string, email: string, phone: string, fullname: string, password: string, service: string, monStart: number, monEnd: number, tueStart: number,
  tueEnd:number, wedStart: number, wedEnd:number, thuStart: number, thuEnd:number, friStart: number, friEnd:number, satStart: number, satEnd:number, sunStart: number,
  sunEnd:number)
{
    this.username = name;
    this.email = email;
    this.phone = phone;
    this.fullname = fullname;
    this.password = password;
    this.service = service;
    this.role = "ROLE_WORKER";
    this.monStart = monStart;
    this.monEnd = monEnd;
    this.tueStart = tueStart;
    this.tueEnd = tueEnd;
    this.wedStart = wedStart;
    this.wedEnd = wedEnd;
    this.thuStart = thuStart;
    this.thuEnd = thuEnd;
    this.friStart = friStart;
    this.friEnd = friEnd;
    this.satStart = satStart;
    this.satEnd = satEnd;
    this.sunStart = sunStart
    this.sunEnd = sunEnd;
  }
}
