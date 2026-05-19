import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, RouterModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  username = '';
  password = '';

  constructor(
    private authService: AuthService,
    private router: Router,
  ) {}

  login() {
    const payload = {
      username: this.username,
      password: this.password,
    };

    console.log('LOGIN PAYLOAD:', payload);

    this.authService.login(payload).subscribe({
      next: (response: any) => {
        console.log('LOGIN SUCCESS:', response);

        if (response?.token) {
          localStorage.setItem('token', response.token);
          alert('Login Successful');

          // redirect after login
          this.router.navigate(['/home']);
        } else {
          alert(response?.error || 'Login failed');
        }
      },

      error: (err) => {
        console.log('LOGIN ERROR:', err);
        alert('Login Failed');
      },
    });
  }
}
