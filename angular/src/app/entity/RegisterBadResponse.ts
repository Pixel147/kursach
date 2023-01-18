export class RegisterBadResponse{
  code:number=0;
  emailMessage:string = '';
  usernameMessage:string = '';
  constructor(code:number,emailMessage:string,usernameMessage:string) {
    this.code = code;
    this.emailMessage = emailMessage;
    this.usernameMessage = usernameMessage;
  }
}
