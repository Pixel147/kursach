export class ownerInfo{
  phone:string;
  fullname:string;
  nameOfCompany:string;
  location:string;
  description:string;



  constructor(phone: string, fullname: string, nameOfCompany:string, location:string, description:string) {
    this.phone = phone;
    this.fullname = fullname;
    this.nameOfCompany = nameOfCompany;
    this.location = location;
    this.description = description;

  }
}
