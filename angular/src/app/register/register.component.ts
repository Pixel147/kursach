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
  nickname = '';
  email = '';
  pass = '';
  repPass = '';
  usernameFlag = false;
  emailFlag = false;
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
    if(this.checkPass() && this.onCheckPassLen() && this.onCheckNickname() && this.checkEmail())
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
    const headers = {'My-Custom-Header': 'foobar'};
    const user = {"username":this.nickname,"email":this.email,"password":this.pass};
    return this.http.post('http://localhost:8080/register', user, {headers:headers}).subscribe(
      (data:any) => {
          this.router.navigate(["/login"]);
      },
      (error:any) => {
            if(error.error.emailMessage == "validation.email.found"){
              this.emailFlag = true;
            }
            if(error.error.usernameMessage == "validation.username.found"){
              this.usernameFlag = true;
            }
      }
    );
  }

}
