export class JobAppointments{
  id:number;
  clientName:string;
  clientPhone:string;
  dateStart:string;
  timeStart:string;
  dateEnd:string;
  timeEnd:string;
  service:string;

  constructor(id: number, clientName: string, clientPhone: string, dateStart: string, timeStart: string, dateEnd: string, timeEnd: string, service: string) {
    this.id = id;
    this.clientName = clientName;
    this.clientPhone = clientPhone;
    this.dateStart = dateStart;
    this.timeStart = timeStart;
    this.dateEnd = dateEnd;
    this.timeEnd = timeEnd;
    this.service = service;
  }
}
