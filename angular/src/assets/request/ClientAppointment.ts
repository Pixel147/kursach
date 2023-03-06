export class ClientAppointment{
  id:number;
  companyName:string;
  workerFullName:string;
  service:string;
  workerPhone:string;
  date:string;
  time:string;

  constructor(id: number, companyName: string, workerFullName: string, service: string, workerPhone: string, date: string, time: string) {
    this.id = id;
    this.companyName = companyName;
    this.workerFullName = workerFullName;
    this.service = service;
    this.workerPhone = workerPhone;
    this.date = date;
    this.time = time;
  }
}
