export class ownerInfo{
  phone:string;
  fullname:string;
  nameOfCompany:string;
  location:string;
  description:string;
  username:string;

  constructor(phone: string, fullname: string, nameOfCompany: string, location: string, description: string, username: string) {
    this.phone = phone;
    this.fullname = fullname;
    this.nameOfCompany = nameOfCompany;
    this.location = location;
    this.description = description;
    this.username = username;
  }
}
