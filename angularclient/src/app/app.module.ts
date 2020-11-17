import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppAuthModule } from './auth/module/app-auth.module';

import { AppComponent } from './app.component';
import { UserListComponent } from './users/componenet/user-list/user-list.component';
import { UserFormComponent } from './users/componenet/user-form/user-form.component';
import { UserService } from './users/service/user.service';
import { LoginComponent } from './auth/component/login/login.component';
import { ProfileComponent } from './auth/component/profile/profile.component';
import { AvatarModule } from "ng2-avatar";
import { SignupComponent } from './auth/component/signup/signup.component';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RedirectHandlerComponent } from './auth/component/redirect-handler/redirect-handler.component';
import { AppAlertModule } from "./alert/module/app-alert.module";
import { CourseListComponent } from './courses/course-list/course-list.component';
import { CourseService } from "./courses/service/course.service";
import { CartComponent } from './cart/component/cart.component';
import { CartService } from "./cart/service/cart.service";
import { CourseComponent } from './courses/course/course.component';
import { UserCourseListComponent } from './courses/userCourse-list/userCourse-list.component';
import { CourseMentorComponent } from './courses/course-mentor/course-mentor.component';
import { FileService } from "./courses/service/file.service";
import { AboutComponent } from "./about/component/about.component";
import { addTutorialComponent } from "./courses/addTutorial/addTutorial.component";
import { editCourseComponent } from "./courses/editCourse/editCourse.component";
import {CategoryService} from "./courses/service/category.service";
import {AddCourseComponent} from "./courses/addCourse/addCourse.component";

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserFormComponent,
    LoginComponent,
    ProfileComponent,
    SignupComponent,
    RedirectHandlerComponent,
    CourseListComponent,
    UserCourseListComponent,
    CartComponent,
    CourseComponent,
    AboutComponent,
    addTutorialComponent,
    editCourseComponent,
    AddCourseComponent,
    CourseMentorComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AppAuthModule,
    ToastrModule.forRoot(),
    AvatarModule,
    BrowserAnimationsModule,
    AppAlertModule
  ],
  providers: [
    UserService,
    CourseService,
    CartService,
    FileService,
    CategoryService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
