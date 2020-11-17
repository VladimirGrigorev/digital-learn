import { Component } from '@angular/core';

import { AuthService } from './auth/service/auth.service';
import { UserInfo } from './users/model/user-info';
import { Router } from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  count: number;
  private currentUser: UserInfo;
  cart: number;

  constructor(
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.authService.currentUser.subscribe((user: UserInfo) => {
      this.currentUser = user;
    })
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  get userInfo() {
    const currentUser = this.authService.getCurrentUser();
    if (!currentUser) {
      return {} as UserInfo;
    }
    return currentUser;
  }

}
