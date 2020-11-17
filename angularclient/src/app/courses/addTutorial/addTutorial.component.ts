import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Course} from "../model/course";
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {CourseService} from "../service/course.service";
import {AuthService} from "../../auth/service/auth.service";
import {ToastrService} from "ngx-toastr";
import {first} from "rxjs/operators";
import {AppComponent} from "../../app.component";
import {FileService} from "../service/file.service";

@Component({
  selector: 'app-course',
  templateUrl: './addTutorial.component.html',
  styleUrls: ['./addTutorial.component.css']
})
export class addTutorialComponent implements OnInit {

  name: string;
  description: string;
  id: number;
  fileForm: FormGroup;
  course: Course = {} as Course;
  private sub: Subscription;

  selectedFiles: FileList;
  currentFileUpload: File;

  constructor(
    private fileService: FileService,
    private appComponent: AppComponent,
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private courseService: CourseService,
    private authService: AuthService,
    private toastr: ToastrService
  ) { }

  selectFile(event) {
    this.selectedFiles = event.target.files;
  }

  ngOnInit(): void {
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
      this.loadCourse(this.id);
    });
    this.fileForm = this.formBuilder.group({
      description: ['', Validators.required],
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
    return this.fileForm.controls;
  }

  onSubmit() {
    // stop here if form is invalid
    if (this.fileForm.invalid) {
      return;
    }

    this.currentFileUpload = this.selectedFiles[0];
    this.fileService.uploadFile(this.currentFileUpload, this.course.id, this.form.name.value,
      this.form.description.value)
      .subscribe(
        () => {
          this.toastr.success("Вы успешно загрузили файл.");
          this.router.routeReuseStrategy.shouldReuseRoute = () => false;
          this.router.onSameUrlNavigation = 'reload';
          this.router.navigate(['course-mentor/' + this.course.id])
        });

  }
}
