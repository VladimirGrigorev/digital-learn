import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs/internal/Subscription';
import { first } from 'rxjs/internal/operators/first';

import { AuthService } from '../../service/auth.service';
import { UserInfo } from '../../../users/model/user-info';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, OnDestroy {

  username: string;
  userInfo: UserInfo = {} as UserInfo;
  private sub: Subscription;
  isAdmin: boolean = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.username = params['username'];
      this.loadUserProfile(this.username);
    });
  }

  loadUserProfile(username: string) {
    this.authService.getUserProfile(username)
      .pipe(first())
      .subscribe( res => {
          this.userInfo = res;
          if (this.userInfo.roles.includes(this.userInfo.roles.find(role => role.name == "ROLE_ADMIN")))
            this.isAdmin = true;
        },
        error => {
          console.log(error);
        });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  logout() {
    this.authService.logout();
    this.router.navigate([''])
  }
}
