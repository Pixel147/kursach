export class LoginResponse
{
  constructor(username:string, type:number) {
    this.username = username;
    this.type = type;
  }
  username:string = "";
  type:number = 0;
}
