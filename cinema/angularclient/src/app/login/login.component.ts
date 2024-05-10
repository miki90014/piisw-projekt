import { Component } from '@angular/core';
import { Login } from '../model/login';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  
  login: Login
  private loginUrl:string;

  constructor(private http: HttpClient, private router: Router){
    this.login = new Login()
    this.loginUrl = 'http://localhost:8080/login';
  }

  onLogin(): void {
    if (this.login.password === '' || this.login.username === '') {
      alert("You must fill form")
    }
    console.log(this.loginUrl, this.login)
    this.http.post(this.loginUrl, this.login).subscribe((res:any)=>{
      if(res.result) {
        alert("Login Success");
        localStorage.setItem('angular17token', res.data.token)
        this.router.navigateByUrl('/cinema-attendent')
      } else {
        alert(res.message)
      }
    })
  }

}
