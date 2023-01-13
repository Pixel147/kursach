import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  constructor(private http: HttpClient) {}
  checkLog = true
  nickname = '';
  email = '';
  pass = '';
  repPass = '';
  type = '';


  onNickname(event:any){
    this.nickname = event.target.value
  }

  onCheckNickname() : boolean
  {
    return this.nickname.length > 4;
  }

  onEmail(event:any)
  {
    this.email = event.target.value
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
    if(this.checkPass() && this.onCheckNickname())
    {
      const user = {"username":this.nickname,"email":this.email,"password":this.pass, "type":this.type};
      this.createUser(user);
    }
    else
    {
      console.log("you invalid");
    }
  }

  createUser(user:Object)
  {
    console.log("created user");
    this.http.post(`http://localhost:8080/register`, user).subscribe(result =>{
      console.log(result);
    }).unsubscribe();
  }
  onTypeEmployee()
  {
    this.type = "employee";
  }

  onTypeClient()
  {
    this.type = "client";
  }
}
