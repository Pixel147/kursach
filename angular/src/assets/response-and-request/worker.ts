export class Worker{
  username:string;
  email:string;
  phone:string;
  fullname:string;
  password:string;
  role:string;
  service:string;
  mondayStart: any = 0;
  mondayEnd: any = 23;
  tuesdayStart: any = 0;
  tuesdayEnd: any = 23;
  wednesdayStart: any = 0;
  wednesdayEnd: any = 23;
  thursdayStart: any = 0;
  thursdayEnd: any = 23;
  fridayStart: any = 0;
  fridayEnd: any = 23;
  saturdayStart: any = 0;
  saturdayEnd: any = 23;
  sundayStart: any = 0;
  sundayEnd: any = 23;


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
    this.mondayStart = monStart;
    this.mondayEnd = monEnd;
    this.tuesdayStart = tueStart;
    this.tuesdayEnd = tueEnd;
    this.wednesdayStart = wedStart;
    this.wednesdayEnd = wedEnd;
    this.thursdayStart = thuStart;
    this.thursdayEnd = thuEnd;
    this.fridayStart = friStart;
    this.fridayEnd = friEnd;
    this.saturdayStart = satStart;
    this.saturdayEnd = satEnd;
    this.sundayStart = sunStart
    this.sundayEnd = sunEnd;
  }
}
