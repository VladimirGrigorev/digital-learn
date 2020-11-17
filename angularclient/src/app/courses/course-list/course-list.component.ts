import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {CourseService} from "../service/course.service";
import {Course} from "../model/course";
import {CartService} from "../../cart/service/cart.service";
import {AuthService} from "../../auth/service/auth.service";
import {UserInfo} from "../../users/model/user-info";
import {Category} from "../model/category";
import {CategoryService} from "../service/category.service";
import {Sorter} from "../../aggregator/sorter/sorter";
import {Filter} from "../../aggregator/filter/filter";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {

  searchForm: FormGroup;
  filter: Filter;
  sorter: Sorter;
  courses: Course[];
  isEnable: boolean;
  currentUser: UserInfo;
  categories: Category[];
  page = 1;
  size = 4;
  countPages;
  isSearch = false;
  course: Course;
  allCourses: Course[];

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private toastr: ToastrService,
    private courseService: CourseService,
    private cartService: CartService,
    private authService: AuthService,
    private categoryService: CategoryService,
  ) {
    this.sorter = new Sorter("trainingRate", "ASC");
    this.filter = null;
  }

  ngOnInit() {
    this.courseService.getCountPages(this.size, this.sorter, this.filter).subscribe( data => {
      this.countPages = Array(data);
    });
    this.courseService.findAll().subscribe(data => {
      this.allCourses = data;
    });
    this.courseService.findAlmostAll(this.page, this.size, this.sorter, this.filter).subscribe(data => {
      this.courses = data;
      this.isEnable = true;
      this.currentUser = this.authService.getCurrentUser();
    });
    this.categoryService.findAll().subscribe(data => {
      this.categories = data;
    });
    this.searchForm = this.formBuilder.group({
      search: ['', Validators.required]
    });
  }

  doFilter(categoryName: string) {
    this.filter = new Filter("categories.*.name", categoryName, "IN");
    this.page = 1;
    this.ngOnInit();
  }

  clearFilter() {
    this.filter = null;
    this.ngOnInit();
  }

  sortASC() {
    this.sorter.direction = "ASC";
    this.ngOnInit();
  }

  sortDesc() {
    this.sorter.direction = "DESC";
    this.ngOnInit();
  }

  sortByRating() {
    this.sorter.property = "trainingRate";
    this.ngOnInit();
  }

  sortByPrice() {
    this.sorter.property = "price";
    this.ngOnInit();
  }

  courseName(course: Course) {
    if (course.name.length > 27) {
      return course.name.substr(0, 27) + "..";
    } else {
      return course.name;
    }
  }

  courseDescription(course: Course) {
    if (course.description.length > 130) {
      return course.description.substr(0, 130) + "..";
    } else {
      return course.description;
    }
  }

  isMentor(): boolean{
    return this.currentUser && this.currentUser.roles.includes(this.currentUser.roles.find(role => role.name == "ROLE_MENTOR"));
  }

  reloadComponent() {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate(['/courses']);
  }

  click(id: string, isCart: boolean) {
    if (isCart && this.isEnable){
      if(this.currentUser && this.currentUser.roles.includes(this.currentUser.roles.find(role => role.name == "ROLE_MENTOR"))) {
        this.router.navigate(['/course-mentor', id]);
      }
      else {
        this.router.navigate(['/course', id]);
      }
    }
    else {
      this.isEnable = true;
    }
    if (!isCart){
      this.isEnable = false;
      this.cartService.toCartTraining(id).subscribe(
        data => {
          this.toastr.success("Вы успешно добавили курс в корзину");
        }, error => {
          this.toastr.error(error.error.message);
          console.log(error);
        });
    }
  }

  isLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  nextPage() {
    if(this.countPages.length > this.page) {
      this.page++;
      this.ngOnInit();
    }
  }

  prevPage() {
    if(this.page > 1) {
      this.page--;
      this.ngOnInit();
    }
  }

  thisPage(number: number) {
    this.page = number;
    this.ngOnInit();
  }

  clickDelete(id: string, isCart: boolean) {
    if (isCart && this.isEnable){
      if(this.currentUser && this.currentUser.roles.includes(this.currentUser.roles.find(role => role.name == "ROLE_MENTOR"))) {
        this.router.navigate(['/course-mentor', id]);
      }
      else {
        this.router.navigate(['/course', id]);
      }
    }
    else {
      this.isEnable = true;
    }
    if (!isCart){
      this.isEnable = false;
      this.courseService.delete(id).subscribe(
        data => {
          this.toastr.success("Вы успешно удалили курс");
          this.reloadComponent();

        }, error => {
          this.toastr.success("Вы успешно удалили курс");
          this.reloadComponent();
          console.log(error);
        });
    }
  }

  get form() {
    return this.searchForm.controls;
  }

  onSubmit() {
    this.course = this.allCourses.find(c => c.name == this.form.search.value);
    this.allCourses.forEach(c => this.courses.pop());
    this.courses.push(this.course);
    this.isSearch = true;
  }
}
