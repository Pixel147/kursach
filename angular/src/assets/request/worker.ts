export class Worker{
  username:string;
  email:string;
  phone:string;
  fullname:string;
  password:string;
  role:string;
  constructor(name: string, email: string, phone: string, fullname: string, password: string) {
    this.username = name;
    this.email = email;
    this.phone = phone;
    this.fullname = fullname;
    this.password = password;
    this.role = "ROLE_WORKER";
  }
}
