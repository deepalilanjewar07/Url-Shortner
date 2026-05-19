import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth';
import { RouterLink } from '@angular/router';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [FormsModule, RouterLink, RouterModule],
  templateUrl: './signup.html',
  styleUrl: './signup.css',
})
export class Signup {
  username = '';
  password = '';

  constructor(private authService: AuthService) {}

  signup() {

    const payload = {
      username: this.username,
      password: this.password
    };

    console.log("PAYLOAD:", payload);

    this.authService.signup(payload).subscribe({

      next: (response) => {

        console.log("SUCCESS:", response);

        alert("Signup Successful");
      },

      error: (err) => {

        console.log("FULL ERROR:", err);

        console.log("STATUS:", err.status);

        console.log("ERROR BODY:", err.error);

        alert("Signup Failed");
      }

    });

  }
}
