export class Worker{
  username:string;
  email:string;
  phone:string;
  fullname:string;
  password:string;
  role:string;
  service:string;


  constructor(name: string, email: string, phone: string, fullname: string, password: string, service: string) {
    this.username = name;
    this.email = email;
    this.phone = phone;
    this.fullname = fullname;
    this.password = password;
    this.service = service;
    this.role = "ROLE_WORKER";
  }
}
