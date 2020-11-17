import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {CourseService} from "../service/course.service";
import {Course} from "../model/course";
import {UserInfo} from "../../users/model/user-info";
import {AuthService} from "../../auth/service/auth.service";
import {Sorter} from "../../aggregator/sorter/sorter";
import {UserService} from "../../users/service/user.service";
import {first} from "rxjs/operators";

@Component({
  selector: 'app-userCourse-list',
  templateUrl: './userCourse-list.component.html',
  styleUrls: ['./userCourse-list.component.css']
})
export class UserCourseListComponent implements OnInit {

  myCourses: Course[];
  recommendationCourses: Course[];
  isEnable: boolean;
  rating;
  currentUser: UserInfo;
  idCourses: string[];
  sorter = new Sorter("trainingRate", "ASC");
  user:UserInfo;

  constructor(
    private router: Router,
    private toastr: ToastrService,
    private courseService: CourseService,
    private userService: UserService,
    private authService: AuthService
  ) {}

  ngOnInit() {
    this.courseService.findAll().subscribe(data => {
      this.myCourses = data;
    });

    this.courseService.findAlmostAll(1, 5, this.sorter, null).subscribe(data => {
      this.recommendationCourses = data;
    });

    this.authService.getUserProfile(this.authService.getCurrentUser().username)
      .pipe(first())
      .subscribe( res => {
        this.user = res;
        this.idCourses = this.user.purchasedTrainings;
        });
    this.isEnable = true;
  }

  click(id: string) {
    // if(this.currentUser.roles.includes(this.currentUser.roles.find(role => role.name == "ROLE_MENTOR"))) {
    //   this.router.navigate(['/course-mentor', id]);
    // }
    // else {
    //   if (this.currentUser.roles.includes(this.currentUser.roles.find(role => role.name == "ROLE_USER")))
    //     this.router.navigate(['/course', id]);
    // }
    this.router.navigate(['/course', id]);
  }

  find(id: string): Course {
    return this.myCourses.find(course => course.id == id);
  }
}
