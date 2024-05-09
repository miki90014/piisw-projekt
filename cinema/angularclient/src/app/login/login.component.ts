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
  constructor(private http: HttpClient, private router: Router){
    this.login = new Login()
  }

  onLogin() {
    this.http.post('https://freeapi.gerasim.in/api/User/Login', this.login).subscribe((res:any)=>{
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
