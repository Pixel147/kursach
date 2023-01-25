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
  emailFlag = false;
  phoneFlag = false;
  passFlag = false;
  companyFlag = false;
  fullnameFlag = false;
  locationFlag = false;
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


  checkIsEmptyLocation()
  {
      if(this.location == '')
      {
        this.locationFlag = true;
        return false;
      }
      this.locationFlag = false;
      return true;
  }

  checkIsEmptyFullName()
  {
    if(this.type == "ROLE_CLIENT")
    {
      if(this.fullNameClient == '')
      {
        this.fullnameFlag = true;
        return false;
      }
      this.fullnameFlag = false;
      return true;
    }
    else if(this.type == "ROLE_EMPLOYEE")
    {
      if(this.fullNameEmployee == '')
      {
        this.fullnameFlag = true;
        return false;
      }
      this.fullnameFlag = false;
      return true;
    }
    else
    {
      if(this.fullNameOwner == '')
      {
        this.fullnameFlag = true;
        return false;
      }
      this.fullnameFlag = false;
      return true;
    }
  }
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
      if(this.expression.test(this.emailClient))
      {
        this.emailFlag = false;
        return true;
      }
      this.emailFlag = true;
      return false;
    }
    else if(this.type == "ROLE_EMPLOYEE")
    {
      if(this.expression.test(this.emailEmployee))
      {
        this.emailFlag = false;
        return true;
      }
      this.emailFlag = true;
      return false;
    }
    else
    {
      if(this.expression.test(this.emailOwner))
      {
        this.emailFlag = false;
        return true;
      }
      this.emailFlag = true;
      return false;
    }

  }
  onCheckPassLen() : boolean
  {
    if(this.type == "ROLE_CLIENT")
    {
      if(this.passClient.length >= 4)
      {
        this.passFlag = false;
        return true;
      }
      this.passFlag = true;
      return false;
    }
    else if(this.type == "ROLE_EMPLOYEE")
    {
      if(this.passEmployee.length >= 4)
      {
        this.passFlag = false;
        return true;
      }
      this.passFlag = true;
      return false;
    }
    else
    {
      if(this.passOwner.length >= 4)
      {
        this.passFlag = false;
        return true;
      }
      this.passFlag = true;
      return false;
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
      if(this.passClient == this.repPassClient)
      {
        this.passFlag = false;
        return true;
      }
      this.passFlag = true;
      return false;
    }
    else if(this.type == "ROLE_EMPLOYEE")
    {
      if(this.passEmployee == this.repPassEmployee)
      {
        this.passFlag = false;
        return true;
      }
      this.passFlag = true;
      return false;
    }
    else
    {
      if(this.passOwner == this.repPassOwner)
      {
        this.passFlag = false;
        return true;
      }
      this.passFlag = true;
      return false;
    }
  }
  onClickReg()
  {
    if(this.checkPass() && this.onCheckPassLen() && this.onCheckNickname() && this.checkEmail() && this.checkIsEmptyLocation() &&
      this.checkIsEmptyFullName())
    {
      this.createUser();
    }
    else
    {
      console.log("Register Error");
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
          if(error.error.usernameMessage == "validation.username.found"){
            this.usernameFlag = true;
          }
          if(error.error.phoneMessage == "validation.phone.found"){
            this.phoneFlag = true;
          }
          if(error.error.companyNameMessage == "validation.company.found")
          {
            this.companyFlag = true;
          }
          if(error.error.emailMessage == "validation.email.found")
          {
            this.emailFlag = true;
          }

          console.log(error);
        }
      });
    }

    return null;

  }

}
