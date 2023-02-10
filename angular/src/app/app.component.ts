import {Component, Injectable} from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "./auth/auth.service";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


@Injectable()
export class AppComponent {
  constructor(
    private http: HttpClient,
    private router: Router,
    private auth:AuthService
    )
  {
    this.CheckToken();
  }


  title = 'angular';
  token: any;
  tokenFlag = false;
  role: any = '';
  userFlag = 1;

  CheckToken()
  {
    if (localStorage.getItem("token") != null)
    {
      this.tokenFlag = true;
      this.auth.login();
    }

    else
    {
      console.log("working");
    }
  }

  CheckUser()
  {
        this.role = localStorage.getItem("role");

        if(this.role == "ROLE_CLIENT")
        {
          this.router.navigate(["/client"]);
        }
        else if(this.role == "ROLE_EMPLOYEE")
        {
          this.router.navigate(["/employee"]);
        }
        else if(this.role == "ROLE_OWNER")
        {
          this.router.navigate(["/ownerInfo"]);
        }
        else
        {
          this.router.navigate(["/admin"]);
        }
    }


  out()
  {
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    localStorage.removeItem("id");
    this.tokenFlag = false;
    this.auth.logout();
    this.router.navigate(["/login"])
  }

}

