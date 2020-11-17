import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Course} from "../model/course";
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {CourseService} from "../service/course.service";
import {AuthService} from "../../auth/service/auth.service";
import {ToastrService} from "ngx-toastr";
import {first} from "rxjs/operators";
import {Category} from "../model/category";
import {CategoryService} from "../service/category.service";

@Component({
  selector: 'app-course',
  templateUrl: './addCourse.component.html',
  styleUrls: ['./addCourse.component.css']
})
export class AddCourseComponent implements OnInit {

  addForm: FormGroup;
  course: Course = {} as Course;
  private sub: Subscription;
  categories: Category[];
  chooseCategoryName = "Категория";
  chooseCategory: Category;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private courseService: CourseService,
    private authService: AuthService,
    private toastr: ToastrService,
    private categoryService: CategoryService,
  ) { }

  ngOnInit(): void {
    this.addForm = this.formBuilder.group({
      description: ['', Validators.required],
      price: ['', [Validators.required, Validators.pattern]],
      name: ['', Validators.required]
    });
    this.categoryService.findAll().subscribe(data => {
      this.categories = data;
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
    return this.addForm.controls;
  }

  onSubmit() {
    // stop here if form is invalid
    if (this.addForm.invalid) {
      return;
    }

    this.courseService.addCourse(this.form.name.value, this.form.description.value, this.form.price.value, this.chooseCategory)
      .pipe(first())
      .subscribe(
        () => {
          this.toastr.success("Вы успешно создали курс.");
          this.router.navigate(['courses'])
        });

  }

  choose(category: Category) {
    this.chooseCategoryName = category.name;
    this.chooseCategory = category;
  }
}
