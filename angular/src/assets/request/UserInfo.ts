export class UserInfo {
  username:string;
  phone:string;
  fullname: string;
  email:string;
  constructor(username:string,phone:string,fullname: string,email:string) {
    this.username = username;
    this.phone = phone;
    this.fullname = fullname;
    this.email = email;
  }
}
