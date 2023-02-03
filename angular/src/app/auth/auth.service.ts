import {Injectable} from "@angular/core";

@Injectable({providedIn:"root"})
export class AuthService{
   private isAuth = false;
   login(){
     this.isAuth = true;
   }
   logout(){
     this.isAuth = false;
   }
   isAuthenticated():Promise<boolean>{
     return new Promise<boolean>(resolve => {
       resolve(this.isAuth)
     });
   }
}
