import { Component, OnInit } from '@angular/core';
import {Course} from "../model/course";
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {CourseService} from "../service/course.service";
import {CartService} from "../../cart/service/cart.service";
import {AuthService} from "../../auth/service/auth.service";
import {ToastrService} from "ngx-toastr";
import {first} from "rxjs/operators";
import {FileService} from "../service/file.service";
import * as fileSaver from 'file-saver';
import {UPLOAD_DIR} from "../../constants/constants";

@Component({
  selector: 'app-course-mentor',
  templateUrl: './course-mentor.component.html',
  styleUrls: ['./course-mentor.component.css']
})
export class CourseMentorComponent implements OnInit {

  id: number;
  course: Course = {} as Course;
  private sub: Subscription;
  rating;
  uploadDir: string;

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

  reloadComponent() {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate(['/course-mentor', this.id]);
  }

  deleteFile(id: string) {
    this.fileService.deleteFile(id).subscribe(data => {
      this.toastr.success("Вы успешно удалили файл");
      this.reloadComponent();
    }, error => {
      console.log(error);
    });
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
