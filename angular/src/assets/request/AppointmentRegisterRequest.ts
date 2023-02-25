export class AppointmentRegisterRequest{
  private time:string = '';
  private workerId:number = 0;
  private date:string = ''
  constructor(time:any,workerId:any,date:any) {
    this.time = time;
    this.workerId = workerId;
    this.date = date;
  }
  public setTime(time:string){
    this.time = time;
  }
  public setWorkerId(id:number){
    this.workerId = id;
  }
  public setDate(date:string){
    this.date = date;
  }
}
