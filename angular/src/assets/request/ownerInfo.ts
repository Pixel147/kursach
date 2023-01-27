export class ownerInfo{
  fullname:string;
  phone:string;
  nameOfCompany:string;

  constructor(phone: string, fullname: string, nameOfCompany:string) {
    this.phone = phone;
    this.fullname = fullname;
    this.nameOfCompany = nameOfCompany;
  }
}
