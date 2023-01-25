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
  nicknameClient = '';
  nicknameEmployee = '';
  nicknameOwner = '';

  emailClient = '';
  emailEmployee = '';
  emailOwner = '';
  passClient = '';
  passEmployee = '';
  passOwner = '';
  repPassClient = '';
  repPassEmployee = '';
  repPassOwner = '';
  fullNameClient = '';
  fullNameEmployee = '';
  fullNameOwner = '';
  companyName = '';
  location = '';
  phoneClient = 0;
  phoneEmployee = 0;
  phoneOwner = 0;
  type = 'ROLE_OWNER';
  radioOwnerFlag = true;
  radioEmployeeFlag = false;
  radioClientFlag = false;
  expression: RegExp = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i;

  onNickname(event:any){
    if(this.type == "ROLE_CLIENT")
    {
      this.nicknameClient = event.target.value;
      this.usernameFlag = false;
    }
    else if(this.type == "ROLE_EMPLOYEE")
    {
      this.nicknameEmployee = event.target.value;
      this.usernameFlag = false;
    }
    else
    {
      this.nicknameOwner = event.target.value;
      this.usernameFlag = false;
    }
  }

  onCheckNickname() : boolean
  {

    if(this.type == "ROLE_CLIENT")
    {
       return this.nicknameClient.length >= 4;
    }
    else if(this.type == "ROLE_EMPLOYEE")
    {
      return this.nicknameEmployee.length >= 4;
    }
    else
    {
      return this.nicknameOwner.length >= 4;
    }
  }

  onEmail(event:any)
  {
    if(this.type == "ROLE_CLIENT")
    {
      this.emailClient = event.target.value;
      this.emailFlag = false;
    }
    else if(this.type == "ROLE_EMPLOYEE")
    {
      this.emailEmployee = event.target.value;
      this.emailFlag = false;
    }
    else
    {
      this.emailOwner = event.target.value;
      this.emailFlag = false;
    }
  }
  checkEmail() : boolean
  {
    if(this.type == "ROLE_CLIENT")
    {
      return this.expression.test(this.emailClient);
    }
    else if(this.type == "ROLE_EMPLOYEE")
    {
      return this.expression.test(this.emailEmployee);
    }
    else
    {
      return this.expression.test(this.emailOwner);
    }

  }
  onCheckPassLen() : boolean
  {
    if(this.type == "ROLE_CLIENT")
    {
      return this.passClient.length >= 4;
    }
    else if(this.type == "ROLE_EMPLOYEE")
    {
      return this.passEmployee.length >= 4;
    }
    else
    {
      return this.passOwner.length >= 4;
    }
  }

  onPass(event:any)
  {
    if(this.type == "ROLE_CLIENT")
    {
      this.passClient = event.target.value;
    }
    else if(this.type == "ROLE_EMPLOYEE")
    {
      this.passEmployee = event.target.value;
    }
    else
    {
      this.passOwner = event.target.value;
    }
  }

  onRepPass(event:any)
  {
    if(this.type == "ROLE_CLIENT")
    {
      this.repPassClient = event.target.value;
    }
    else if(this.type == "ROLE_EMPLOYEE")
    {
      this.repPassEmployee = event.target.value;
    }
    else
    {
      this.repPassOwner = event.target.value;
    }
  }

  onFullName(event:any)
  {
    if(this.type == "ROLE_CLIENT")
    {
      this.fullNameClient = event.target.value;
    }
    else if(this.type == "ROLE_EMPLOYEE")
    {
      this.fullNameEmployee = event.target.value;
    }
    else
    {
      this.fullNameOwner = event.target.value;
    }
  }

  onCompanyName(event:any)
  {
    this.companyName = event.target.value;
  }

  onLocation(event:any)
  {
    this.location = event.target.value;
  }

  onPhone(event:any)
  {
    if(this.type == "ROLE_CLIENT")
    {
      this.phoneClient = event.target.value;
    }
    else if(this.type == "ROLE_EMPLOYEE")
    {
      this.phoneEmployee = event.target.value;
    }
    else
    {
      this.phoneOwner = event.target.value;
    }
  }

  checkPass() : boolean
  {

    if(this.type == "ROLE_CLIENT")
    {
      return this.passClient == this.repPassClient;
    }
    else if(this.type == "ROLE_EMPLOYEE")
    {
      return this.passEmployee == this.repPassEmployee;
    }
    else
    {
      return this.passOwner == this.repPassOwner;
    }
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
      const user = {"username":this.nicknameClient,"email":this.emailClient,"password":this.passClient, "role":this.type,
        "fullname": this.fullNameClient, "phone":this.phoneClient};
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
      const user = {"username":this.nicknameEmployee,"email":this.emailEmployee,"password":this.passEmployee, "role":this.type,
        "fullname": this.fullNameEmployee, "phone":this.phoneEmployee};
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

      const user = {"username":this.nicknameOwner,"email":this.emailOwner,"password":this.passOwner, "role":this.type,
      "fullname": this.fullNameOwner, "company_name":this.companyName, "location":this.location, "phone":this.phoneOwner};
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
