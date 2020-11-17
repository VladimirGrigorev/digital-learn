import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserListComponent } from './users/componenet/user-list/user-list.component';
import { UserFormComponent } from './users/componenet/user-form/user-form.component';
import { LoginComponent } from './auth/component/login/login.component';
import { ProfileComponent } from './auth/component/profile/profile.component';
import { SignupComponent } from './auth/component/signup/signup.component';
import { RedirectHandlerComponent } from './auth/component/redirect-handler/redirect-handler.component';
import { CourseListComponent } from "./courses/course-list/course-list.component";
import { UserCourseListComponent } from "./courses/userCourse-list/userCourse-list.component";
import { CartComponent } from "./cart/component/cart.component";
import { CourseComponent } from "./courses/course/course.component";
import {CourseMentorComponent} from "./courses/course-mentor/course-mentor.component";
import { AboutComponent } from "./about/component/about.component";
import { addTutorialComponent } from "./courses/addTutorial/addTutorial.component";
import { editCourseComponent } from "./courses/editCourse/editCourse.component";
import {AddCourseComponent} from "./courses/addCourse/addCourse.component";

const routes: Routes = [
  { path: '', component: CourseListComponent },
  { path: 'login', component: LoginComponent },
  { path: 'users/:username', component: ProfileComponent },
  { path: 'users', component: UserListComponent },
  { path: 'adduser', component: UserFormComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'oauth2/redirect', component: RedirectHandlerComponent },
  { path: 'courses', component: CourseListComponent },
  { path: 'cart', component: CartComponent },
  { path: 'course/:id', component: CourseComponent },
  { path: 'my-courses', component: UserCourseListComponent },
  { path: 'about', component: AboutComponent },
  { path: 'course-mentor/:id/edit', component: editCourseComponent },
  { path: 'course-mentor/:id/addTutorial', component: addTutorialComponent },
  { path: 'course-mentor/:id', component: CourseMentorComponent },
  { path: 'add-course', component: AddCourseComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
