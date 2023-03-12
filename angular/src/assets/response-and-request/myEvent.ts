export class MyEvent {
  public id:string;
  public title:string;
  public start:string;
  public end:string;

  constructor(id: string, title: string, start: string, end: string) {
    this.id = id;
    this.title = title;
    this.start = start;
    this.end = end;
  }
}
