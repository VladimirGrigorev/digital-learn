import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { first } from 'rxjs/internal/operators/first';
import { AuthService } from '../../service/auth.service';
import { GOOGLE_AUTH_URL, GITHUB_AUTH_URL } from '../../../constants/constants';
import {ToastrService} from "ngx-toastr";
import {AlertService} from "../../../alert/service/alert.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  readonly GOOGLE_AUTH_URL = GOOGLE_AUTH_URL;
  readonly GITHUB_AUTH_URL = GITHUB_AUTH_URL;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private toastr: ToastrService,
    private authService: AuthService,
    private alertService: AlertService
  ) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });

    // get return url from route parameters or default to '/'
  }

  // convenience getter for easy access to form fields
  get f() { return this.loginForm.controls; }

  onSubmit() {
    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    this.authService.login(this.f.email.value, this.f.password.value)
      .pipe(first())
      .subscribe(
        () => {
          this.toastr.success("Вы успешно вошли");
          this.router.navigate(['']);
            // .then(() => {
            // window.location.reload();
        },
        error => {
          this.alertService.error(error.error.message);
        });
  }

}
