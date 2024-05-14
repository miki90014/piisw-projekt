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
    this.http.post(this.loginUrl, this.login, { responseType: 'text' }).subscribe((token: string)=>{
        console.log('Token received:', token);
        alert("Login Success");
        localStorage.setItem('token', token);
        this.router.navigateByUrl('/cinema-attendant')
        //TODO: Pamiętać aby usuwać token z localStroage przy wylogowaniu localStorage.removeItem(this.tokenKey);
      },
      (error) => {
        alert("Login Failed");
        console.error('Login failed:', error);
      })
    
  }

}
