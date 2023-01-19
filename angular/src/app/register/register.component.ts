import { Component } from '@angular/core';
import {HttpClient, HttpRequest, HttpResponse} from "@angular/common/http";
import {User} from "../entity/user";
import {Router} from "@angular/router";
import {RegisterBadResponse} from "../entity/RegisterBadResponse";

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
  type = 0;
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

  checkPass() : boolean
  {
    return this.pass == this.repPass;
  }
  onClickReg()
  {
    if(this.checkPass() && this.onCheckPassLen() && this.onCheckNickname() && this.checkEmail() && this.type != 0)
    {
      this.createUser();
    }
    else
    {
      console.log("you invalid");
    }
  }

  onTypeEmployee()
  {
    this.type = 2;
  }

  onTypeClient()
  {
    this.type = 1;
  }

  createUser()
  {
    const headers = {'My-Custom-Header': 'foobar'};
    const user:User = {"username":this.nickname,"email":this.email,"password":this.pass, "userType":this.type};
    return this.http.post('http://localhost:8080/register', user, {headers:headers}).subscribe({

      error: error => {
        this.emailMessage = error.error.messageEmail;
        this.usernameMessage = error.error.messageUsername;
        if(this.emailMessage == "validation.email.found")
        {
          console.log("validation.email.found");
          this.emailFlag = true;
        }
        if(this.usernameMessage == "validation.username.found")
        {
          console.log("validation.username.found")
          this.usernameFlag = true;
        }
      },
      next:(data: any) => {
        if(data.type == 1)
        {
          this.router.navigate(["/client"]);
        }
        else if(data.type == 2)
        {
          this.router.navigate(["/employee"])
        }
      }
    });
  }

}
