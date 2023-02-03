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
    this.getOwnerInfo();
  }
  ownerInfo:ownerInfo| any;
  token: any;
  fullname: any;
  username: any;
  companyName:any;
  phoneOwner:any;
  location:any;
  description:any = '';
  descriptionFlag = false;
  textAreaDescriptionText = '';
  editDescription(){
    this.textAreaDescriptionText = this.description;
    this.descriptionFlag = true;
  }
  updateDescription(){
    this.token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.token}`
    })
    this.http.put("http://localhost:8080/owner/description",this.textAreaDescriptionText,{headers: headers}).subscribe({
      next:(data:any) => {
        console.log(data);
      },
      error:(err:any) =>{
        console.log(err)
      }
    })
    this.description = this.textAreaDescriptionText;
    this.descriptionFlag = false;
  }
  getOwnerInfo(){
      this.token = localStorage.getItem('token');
      const headers = new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      })
      this.ownerInfo = new ownerInfo(this.phoneOwner, this.fullname, this.companyName, this.location, this.description, this.username)
      this.http.get('http://localhost:8080/ownerInfo',{headers: headers}).subscribe(
        {
          next:(data:any) => {
            this.companyName = data.companyName;
            this.fullname = data.fullname;
            this.phoneOwner = data.phone;
            this.location = data.location;
            this.description = data.description;
            this.username = data.username;
          },
          error:(err:any) =>{
            console.log(err);
          }
        },
      );
  }
}
