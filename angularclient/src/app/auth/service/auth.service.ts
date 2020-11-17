import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { shareReplay, tap, first } from 'rxjs/operators';
import { Subject } from 'rxjs';

import { UserInfo } from '../../users/model/user-info';
import {API_BASE_URL} from "../../constants/constants";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  currentUser: Subject<UserInfo> = new Subject<UserInfo>();

  constructor(private http: HttpClient) { }

  register(user : UserInfo) {
    return this.http.post(API_BASE_URL + `/auth` + `/signup`, user);
  }

  login(email: string, password: string) {
    return this.http.post<any>(API_BASE_URL + `/auth` + '/login', {"email": email, "password": password})
      .pipe(tap(res => this.setSession(res.accessToken)), // handles the auth result
        shareReplay()); // prevents the receiver of this Observable from accidentally triggering multiple POST requests due to multiple subscriptions.
  }

  loginWithToken(token){
    this.setSession(token);
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('accessToken');
    localStorage.removeItem('currentUser');
    this.currentUser.next(undefined);
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('accessToken');
  }

  getUserProfile(username): any {
    return this.http.get(API_BASE_URL + `/users/${username}`);
  }

  getCurrentUser(): UserInfo {
    return JSON.parse(localStorage.getItem('currentUser')) as UserInfo;
  }

  private setSession(token) {
    localStorage.setItem('accessToken', token);

    this.http.get(API_BASE_URL + '/users/me')
      .pipe(first())
      .subscribe((user: UserInfo) => {
          localStorage.setItem('currentUser', JSON.stringify(user));
          this.currentUser.next(user);
        },
        error => {
          console.log(error);
        });
  }

  checkUsernameAvailability(username: string) {
    return this.http.get<any>(API_BASE_URL + `/users/checkUsernameAvailability?username=${username}`);
  }

  checkEmailAvailability(email: string) {
    return this.http.get<any>(API_BASE_URL + `/users/checkEmailAvailability?email=${email}`);
  }

}
