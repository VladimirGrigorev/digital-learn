import { Component, OnInit } from '@angular/core';
import { UserInfo } from '../../model/user-info';
import { UserService } from '../../service/user.service';
import {ToastrService} from "ngx-toastr";
import {Router} from "@angular/router";
import {AuthService} from "../../../auth/service/auth.service";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  title: string;
  users: UserInfo[];
  currentUser: UserInfo;

  constructor(
    private router: Router,
    private toastr: ToastrService,
    private userService: UserService,
    private authService: AuthService,
  ) {
    this.title = 'Управление пользоватлями';
  }

  ngOnInit() {
    this.userService.findAll().subscribe(data => {
      this.users = data;
    });
    this.currentUser = this.authService.getCurrentUser();
  }

  reloadComponent() {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate(['/users']);
  }

  block(id) {
    this.userService.block(this.users.find(x => x.id == id).email).subscribe(data => {
      this.toastr.success("Вы успешно забанили / разбанили пользователя");
      this.reloadComponent();
    });
  }
}
