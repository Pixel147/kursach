import {Component, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
@Injectable()
export class LoginComponent {
  constructor(private http: HttpClient, private router: Router) {}
  username:string = '';
  password:string = '';
  token:string = "";
  onCheckUsername() : boolean
  {
    return this.username.length >= 4;
  }
  onCheckPassLen() : boolean
  {
    return this.password.length >= 4;
  }
  onInputUsername(event:any)
  {
    this.username = event.target.value;
  }
  onInputPassword(event:any)
  {
    this.password = event.target.value;
  }
  logIntoAccount()
  {
    console.log("try to login user");
    const user:{ password: string; username: string } = {"username":this.username,"password":this.password};
    this.http.post(`http://localhost:8080/login`, user).subscribe({
      next:(data: any) => {
          localStorage.setItem("token", data.token);
          this.router.navigate(["/"]);
      },
      error: error => console.log(error),
    });
  }
  login() {
    if(this.onCheckUsername() && this.onCheckPassLen())
    {
      this.logIntoAccount();
    }
    else
    {
      console.log("you invalid");
    }

  }
}
