import {Component, Injectable} from '@angular/core';
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


@Injectable()
export class AppComponent {
  constructor(private http: HttpClient, private router: Router) {
    this.CheckToken();
  }


  title = 'angular';
  tokenFlag = false;
  role: any = '';
  userFlag = 1;

  CheckToken()
  {
    if (localStorage.getItem("token") != null)
    {
      this.tokenFlag = true;
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
          this.router.navigate(["/company_owner"]);
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
    this.tokenFlag = false;
    this.router.navigate(["/login"])
  }

}

