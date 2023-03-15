import {Component} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HttpHeaders} from "@angular/common/http";
import {ownerInfo} from "../../assets/response-and-request/ownerInfo";


@Component({
  selector: 'app-owner-info-panel',
  templateUrl: './owner-info-panel.component.html',
  styleUrls: ['./owner-info-panel.component.css']
})
export class OwnerInfoPanelComponent {
  constructor(private http: HttpClient) {
    this.loadOwnerInfo();
  }
  ownerInfo:ownerInfo = new ownerInfo("","","",'','','');
  token: any;
  descriptionEditingFlag = false;
  textAreaDescriptionText = '';
  clickedOnEdit():void{
    this.textAreaDescriptionText = this.ownerInfo.description;
    this.descriptionEditingFlag = true;
  }
  updateDescription():void{
    this.token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${this.token}`
    })
    this.http.put(`http://localhost:8080/owner/${localStorage.getItem("id")}/description`,this.textAreaDescriptionText,{headers: headers}).subscribe({
      next:(data:any) => {
        console.log(data);
      },
      error:(err:any) =>{
        console.log(err)
      }
    })
    this.ownerInfo.description = this.textAreaDescriptionText;
    this.descriptionEditingFlag = false;
  }
  loadOwnerInfo():void{
      this.token = localStorage.getItem('token');
      const headers = new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      })
      this.http.get(`http://localhost:8080/owner/${localStorage.getItem("id")}`,{headers: headers}).subscribe(
        {
          next:(data:any) => {
            this.ownerInfo = new ownerInfo(data.phone, data.fullname, data.companyName, data.location, data.description, data.username)
          },
          error:(err:any) =>{
            console.log(err);
          }
        },
      );
  }
}
