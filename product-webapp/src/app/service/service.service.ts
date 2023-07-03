import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Authentication } from '../model/Authentication';


@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  baseUrl:string="http://localhost:8082";

  constructor(private http:HttpClient) { }


//method to check if the user is login
login(loginData:Authentication){
  return this.http.post<any>(this.baseUrl+'/login',loginData);
}

}
