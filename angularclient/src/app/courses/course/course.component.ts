import { Component, OnInit } from '@angular/core';
import {Course} from "../model/course";
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {CourseService} from "../service/course.service";
import {first} from "rxjs/operators";
import {CartService} from "../../cart/service/cart.service";
import {AuthService} from "../../auth/service/auth.service";
import {UPLOAD_DIR} from "../../constants/constants";
import {FileService} from "../service/file.service";
import * as fileSaver from 'file-saver';
import {UserInfo} from "../../users/model/user-info";

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css']
})
export class CourseComponent implements OnInit {

  id: string;
  course: Course = {} as Course;
  private sub: Subscription;
  rating;
  uploadDir: string;
  user: UserInfo;
  idCourses: string[];
  isBlock = false;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private courseService: CourseService,
    private cartService: CartService,
    private authService: AuthService,
    private toastr: ToastrService,
    private fileService: FileService
  ) { }

  ngOnInit() {
    this.uploadDir = UPLOAD_DIR;
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
      this.loadCourse(this.id);
    });

    this.authService.getUserProfile(this.authService.getCurrentUser().username)
      .pipe(first())
      .subscribe( res => {
        this.user = res;
        this.idCourses = this.user.purchasedTrainings;
        if(this.idCourses.find(idCourse => {
         return this.id == idCourse;})){
          this.isBlock = true;
        }
      });
  }

  loadCourse(id) {
    this.courseService.findById(id)
      .pipe(first())
      .subscribe( res => {
          this.course = res;
        },
        error => {
          console.log(error);
        });
  }

  toCart(id: string) {
    this.cartService.toCartTraining(id).subscribe(
      data => {
        this.toastr.success("Вы успешно добавили курс в корзину");
      }, error => {
        this.toastr.error(error.error.message);
        console.log(error);
      });
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  downloadFile(nameFile: string) {
    this.fileService.download(nameFile).subscribe(response => {
      this.toastr.success("Вы успешно скачали файл");
      let blob:any = new Blob([response.slice()], { type: response.type });
      const url = window.URL.createObjectURL(blob);
      fileSaver.saveAs(blob, nameFile);
    }, error => {
      console.log(error);
      this.toastr.error("Что-то пошло не так");
    });
  }
}
