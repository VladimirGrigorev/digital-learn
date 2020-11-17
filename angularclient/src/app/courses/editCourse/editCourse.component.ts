import {Component, OnInit} from "@angular/core";
import {Course} from "../model/course";
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {CourseService} from "../service/course.service";
import {AuthService} from "../../auth/service/auth.service";
import {ToastrService} from "ngx-toastr";
import {first} from "rxjs/operators";
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-course',
  templateUrl: './editCourse.component.html',
  styleUrls: ['./editCourse.component.css']
})
export class editCourseComponent implements OnInit {

  id: number;
  editForm: FormGroup;
  course: Course = {} as Course;
  private sub: Subscription;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private courseService: CourseService,
    private authService: AuthService,
    private toastr: ToastrService,
  ) { }

  ngOnInit(): void {
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
      this.loadCourse(this.id);
    });
    this.editForm = this.formBuilder.group({
      description: ['', Validators.required],
      price: ['', [Validators.required, Validators.pattern]],
      name: ['', Validators.required]
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

  get form() {
    return this.editForm.controls;
  }

  onSubmit() {
    // stop here if form is invalid
    if (this.editForm.invalid) {
      return;
    }

    this.courseService.editCourse(this.course.id, this.form.name.value, this.form.description.value, this.form.price.value)
      .pipe(first())
      .subscribe(
        () => {
          this.toastr.success("Вы успешно изменили курс.");
          this.router.navigate(['course-mentor/' + this.course.id])
        });
  }
}
