import { Role } from "./Role";

export interface User{
	userId:number;
     firstName:string;
     lastName:string;
	 phoneNumber:string;
	 userName:string;
	 password:string;
	 terms?: boolean; 
	 role:Role;
}