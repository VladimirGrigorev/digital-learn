import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {first, map} from 'rxjs/operators';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../../service/auth.service';
import { GOOGLE_AUTH_URL, GITHUB_AUTH_URL } from '../../../constants/constants';
import { AlertService } from '../../../alert/service/alert.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  registerForm: FormGroup;
  readonly GOOGLE_AUTH_URL = GOOGLE_AUTH_URL;
  readonly GITHUB_AUTH_URL = GITHUB_AUTH_URL;

  constructor(
    private formBuilder: FormBuilder,
    private toastr: ToastrService,
    private router: Router,
    private authService: AuthService,
    private alertService: AlertService
  ) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      username: [
        '',
        [Validators.required, Validators.minLength(4), Validators.maxLength(15)],
        this.validateUsernameAvailability.bind(this)
      ],
      email: [
        '',
        [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$")],
        this.validateEmailNotTaken.bind(this)
      ],
      password: ['', [Validators.required, Validators.minLength(4), Validators.maxLength(20)]]
    })
  }

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  onFormSubmit() {
    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }
    this.authService.register(this.registerForm.value)
      .pipe(first())
      .subscribe(
        data => {
          this.toastr.success("Вы успешно зарегистрировались. Войдите для продолжения!");
          this.router.navigate(['/login']);
        },
        error => {
          this.alertService.error(error || "Что-то пошло не так");
        });
  }

  validateUsernameAvailability(control: AbstractControl) {
    return this.authService.checkUsernameAvailability(control.value).pipe(map(res => {
      return res.available ? null : { usernameTaken: true };
    }));
  }

  validateEmailNotTaken(control: AbstractControl) {
    return this.authService.checkEmailAvailability(control.value).pipe(map(res => {
      return res.available ? null : { emailTaken: true };
    }));
  }
}
