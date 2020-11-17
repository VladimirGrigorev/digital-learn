import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserInfo } from '../model/user-info';
import { Observable } from 'rxjs';
import {API_BASE_URL} from "../../constants/constants";

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<UserInfo[]> {
    return this.http.get<UserInfo[]>(API_BASE_URL + `/users`);
  }

  public save(user: UserInfo) {
    return this.http.post<UserInfo>(API_BASE_URL + `/users`, user);
  }

  public update(user: UserInfo) {
    return this.http.put<UserInfo>(API_BASE_URL + `/users`, user);
  }

  block(email: string) {
    return this.http.post<String>(API_BASE_URL + `/users` + `/blockOrUnblock`, email);
  }
}
