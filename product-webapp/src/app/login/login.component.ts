import { NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Authentication } from '../model/Authentication';
import { ServiceService } from '../service/service.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],

})
export class LoginComponent implements OnInit {
  loginDetails:Authentication=new Authentication();

hide: boolean = true;
email = new FormControl('', [Validators.required, Validators.email]);


  //add constructor
  constructor(private router:Router,private service:ServiceService) { }

  loginForm = new FormGroup({
    email: new FormControl('',Validators.required),
    password: new FormControl('',Validators.required)
  });
  

  ngOnInit() {
  }

//method onSubmit that will navigate to the home page
  onSubmit() {
    
    this.loginDetails.email=this.loginForm.value.email!;
    this.loginDetails.password=this.loginForm.value.password!;

    this.service.login(this.loginDetails).subscribe((response :any) =>{
    console.log(response);
    

    console.log("Login successful");
    this.router.navigate(['/home']);
  })
  }


  getErrorMessage() {
    if (this.email.hasError('required')) {
      return 'You must enter a value';
    }

    return this.email.hasError('email') ? 'Not a valid email' : '';
  }
}
