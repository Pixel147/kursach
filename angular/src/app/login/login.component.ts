import {Component, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
@Injectable()
export class LoginComponent {
  constructor(private http: HttpClient) {}
  username:string = '';
  password:string = '';
  onInputUsername(event:any)
  {
    this.username = event.target.value;
  }
  onInputPassword(event:any)
  {
    this.password = event.target.value;
  }
  logIntoAccount(user:Object)
  {
    console.log("sign in");
    this.http.post(`http://localhost:8080/login`, user).subscribe(result =>{
      console.log(result);
    }).unsubscribe();
  }
  login() {
    console.log("login clicked");
    const body = {username: this.username, password: this.password};
    this.logIntoAccount(body);
  }
}
