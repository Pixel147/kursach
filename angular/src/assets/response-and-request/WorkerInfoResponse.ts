export class WorkerInfoResponse{
  username:string;
  fullname:string;
  email:string;
  phone:string;
  workStartsIn:string;
  workEndsIn:string;
  mondayFlag:any;
  tuesdayFlag:any;
  wednesdayFlag:any;
  thursdayFlag:any;
  fridayFlag:any;
  saturdayFlag:any;
  sundayFlag:any;


  constructor() {
    this.username = '';
    this.fullname = '';
    this.email = '';
    this.phone = '';
    this.workStartsIn = '';
    this.workEndsIn = '';
    this.mondayFlag = null;
    this.tuesdayFlag = null;
    this.wednesdayFlag = null;
    this.thursdayFlag = null;
    this.fridayFlag = null;
    this.saturdayFlag = null;
    this.sundayFlag = null;
  }
}
