import {Component, Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";

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
  login() {
    /*console.log("login clicked");
    const body = {username: this.username, password: this.password};
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json','Cache-Control': 'no-cache' })};
    return this.http.post('http://localhost:8080/login', body, httpOptions);*/
  }
}
