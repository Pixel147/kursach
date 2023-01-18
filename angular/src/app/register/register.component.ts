import { Component } from '@angular/core';
import {HttpClient, HttpRequest, HttpResponse} from "@angular/common/http";
import {User} from "../entity/user";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  constructor(private http: HttpClient, private router: Router) {}
  recivedUser:User | any;
  checkLog = true;
  nickname = '';
  email = '';
  pass = '';
  repPass = '';
  type = 0;
  user:any;
  result : boolean = false;
  expression: RegExp = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i;

  onNickname(event:any){
    this.nickname = event.target.value
  }

  onCheckNickname() : boolean
  {
    return this.nickname.length >= 4;
  }

  onEmail(event:any)
  {
    this.email = event.target.value
  }
  checkEmail() : boolean
  {
    this.result = this.expression.test(this.email);
    return this.result;
  }
  onCheckPassLen() : boolean
  {
    return this.pass.length >= 4;
  }
  onPass(event:any){
    this.pass = event.target.value
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

  createUser()
  {
    console.log("user created");
    const headers = {'My-Custom-Header': 'foobar'};
    const user:User = {"username":this.nickname,"email":this.email,"password":this.pass, "userType":this.type};
    return this.http.post('http://localhost:8080/register', user, {headers:headers}).subscribe({
      error: error => {
        console.log(error);
        //todo if for red border
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
  onTypeEmployee()
  {
    this.type = 2;
  }

  onTypeClient()
  {
    this.type = 1;
  }
}
