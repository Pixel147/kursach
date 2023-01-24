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
  constructor(private http: HttpClient, private router: Router) {}


  title = 'angular';
  tokenFlag = false;
  type:number = 1;
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

  CheckUser() //todo page for diferent users
  {
    // this.http.post(`http://localhost:8080/user_type`, localStorage.getItem("token")).subscribe({
    //   next:(data: any) => {

        if(this.type == 1)
        {
          this.router.navigate(["/client"]);
        }
        else if(this.type == 2)
        {
          this.router.navigate(["/employee"]);
        }
        else if(this.type == 3)
        {
          this.router.navigate(["/company_owner"]);
        }
        else
        {
          this.router.navigate(["/admin"]);
        }
    }
    // ,
    //   error: error => console.log(error),
    //
    // });
    // }


  out()
  {
    localStorage.removeItem("token");
    this.tokenFlag = false;
    this.router.navigate(["/login"])
  }

}

