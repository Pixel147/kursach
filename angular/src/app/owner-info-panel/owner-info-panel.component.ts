import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";
import {ownerInfo} from "../../assets/request/ownerInfo";


@Component({
  selector: 'app-owner-info-panel',
  templateUrl: './owner-info-panel.component.html',
  styleUrls: ['./owner-info-panel.component.css']
})
export class OwnerInfoPanelComponent {
  constructor(private http: HttpClient) {
  }
  ownerInfo:ownerInfo| any;
  token: any;
  fullname: any;
  companyName:any;
  phoneOwner:any;
  dataValidation():boolean{
    console.log("fullname :", this.fullname);
    console.log("phone :", this.phoneOwner);
    console.log("CompanyName :", this.companyName);
    return this.fullname != '' && this.phoneOwner != '' && this.companyName != '';
  }
  createWorker(){
    if(this.dataValidation()){
      this.token = localStorage.getItem('token');
      const headers = new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      })
      this.ownerInfo = new ownerInfo(this.phoneOwner, this.fullname, this.companyName)
      this.http.get('http://localhost:8080/register/ownerInfo',{headers: headers}).subscribe(
        {
          next:(data:any) => {
            console.log(data);
          },
          error:(err:any) =>{
            console.log(err);
          }
        },


      )
    }
    else{
      console.log("invalid data");
    }
  }
}
