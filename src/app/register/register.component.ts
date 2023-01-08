import { Component } from '@angular/core';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  checkLog = true
  checkPass = true
  nickname = '';
  email = '';
  pass = '';
  repPass = '';


  onNickname(event:any){

    this.nickname = event.target.value
    if (this.nickname.length < 4)
    {
      this.checkLog = false
    }

    else
    {
      this.checkLog = true
    }

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

  onClick()
  {
    console.log(this.nickname)
    console.log(this.email)
    console.log(this.pass)
    console.log(this.repPass)
  }


}
