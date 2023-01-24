import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  constructor(private http: HttpClient, private router: Router) {}
  usernameFlag = false;
  usernameColorRed = 'red';
  usernameColorGrey = 'grey';
  usernameBorder2px = '2px';
  usernameBorder1px = '1px';
  emailFlag = false;
  emailColorRed = 'red';
  emailColorGrey = 'grey';
  emailBorder2px = '2px';
  emailBorder1px = '1px';
  emailMessage = '';
  usernameMessage = '';
  nickname = '';
  email = '';
  pass = '';
  repPass = '';
  fullName = '';
  companyName = '';
  location = '';
  roleInCompany = '';
  phone = 0;
  type = 'ROLE_OWNER';
  radioOwnerFlag = true;
  radioEmployeeFlag = false;
  radioClientFlag = false;
  result : boolean = false;
  expression: RegExp = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i;

  onNickname(event:any){
    this.nickname = event.target.value;
    this.usernameFlag = false;
  }

  onCheckNickname() : boolean
  {
    return this.nickname.length >= 4;
  }

  onEmail(event:any)
  {
    this.email = event.target.value;
    this.emailFlag = false;
  }
  checkEmail() : boolean
  {
    return this.expression.test(this.email);
  }
  onCheckPassLen() : boolean
  {
    return this.pass.length >= 4;
  }
  onPass(event:any){
    this.pass = event.target.value;
  }

  onRepPass(event:any)
  {
    this.repPass = event.target.value
  }

  onFullName(event:any)
  {
    this.fullName = event.target.value;
  }

  onCompanyName(event:any)
  {
    this.companyName = event.target.value;
  }

  onLocation(event:any)
  {
    this.location = event.target.value;
  }

  onRoleInCompany(event:any)
  {
    this.roleInCompany = event.target.value;
  }

  onPhone(event:any)
  {

    this.phone = event.target.value;
  }

  checkPass() : boolean
  {
    return this.pass == this.repPass;
  }
  onClickReg()
  {
    if(this.checkPass() && this.onCheckPassLen() && this.onCheckNickname() && this.checkEmail() && this.type != '')
    {
      this.createUser();
    }
    else
    {
      console.log("you invalid");
    }
  }

  onTypeOwner()
  {
    this.type = "ROLE_OWNER";
    this.radioOwnerFlag = true;
    this.radioEmployeeFlag = false;
    this.radioClientFlag = false;

  }
  onTypeEmployee()
  {
    this.type = "ROLE_EMPLOYEE";
    this.radioOwnerFlag = false;
    this.radioEmployeeFlag = true;
    this.radioClientFlag = false;
  }

  onTypeClient()
  {
    this.type = "ROLE_CLIENT";
    this.radioOwnerFlag = false;
    this.radioEmployeeFlag = false;
    this.radioClientFlag = true;
  }

  createUser()
  {
    const headers = {'My-Custom-Header': 'foobar'};
    console.log(this.type);
    if(this.type == "ROLE_CLIENT")
    {
      const user = {"username":this.nickname,"email":this.email,"password":this.pass, "role":this.type,
        "fullname": this.fullName, "phone":this.phone};
      return this.http.post('http://localhost:8080/register/client', user, {headers:headers}).subscribe({
        next:(data: any) => {
          this.router.navigate(["/login"]);
        },
        error:(error : any) => {
          console.log(error);
        }
      });
    }

    else if(this.type == "ROLE_EMPLOYEE")
    {
      const user = {"username":this.nickname,"email":this.email,"password":this.pass, "role":this.type,
        "fullname": this.fullName, "company_name":this.companyName, "roleInCompany":this.roleInCompany, "phone":this.phone};
      return this.http.post('http://localhost:8080/register/employee', user, {headers:headers}).subscribe({
        next:(data: any) => {
          this.router.navigate(["/login"]);
        },
        error:(error : any) => {
          console.log(error);
        }
      });
    }

    else if(this.type == "ROLE_OWNER")
    {

      const user = {"username":this.nickname,"email":this.email,"password":this.pass, "role":this.type,
      "fullname": this.fullName, "company_name":this.companyName, "location":this.location, "phone":this.phone};
      return this.http.post('http://localhost:8080/register/owner', user, {headers:headers}).subscribe({
        next:(data: any) => {
          this.router.navigate(["/login"]);
        },
        error:(error : any) => {
          console.log(error);
        }
      });
    }

    return null;

  }

}
