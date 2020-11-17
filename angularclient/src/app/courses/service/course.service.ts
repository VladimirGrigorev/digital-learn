import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {API_BASE_URL} from "../../constants/constants";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Course} from "../model/course";;
import {Category} from "../model/category";
import {Sorter} from "../../aggregator/sorter/sorter";
import {Filter} from "../../aggregator/filter/filter";

@Injectable()
export class CourseService {
  private rating: any;

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Course[]> {
    return this.http.get<Course[]>(API_BASE_URL + `/trainings`);
  }

  public findAlmostAll(page, size, sorter: Sorter, filter: Filter): Observable<Course[]> {
    if (filter === null) {
      const params = new HttpParams()
        .set('page', page.toString())
        .set('size', size.toString())
        .set('sorterProperty', sorter.property)
        .set('sorterDirection', sorter.direction)
        .set('filterProperty', null)
        .set('filterValue', null)
        .set('filterOperator', null);

      return this.http.get<Course[]>(API_BASE_URL + `/trainings/aggregate`, {params});
    } else {
      const params = new HttpParams()
        .set('page', page.toString())
        .set('size', size.toString())
        .set('sorterProperty', sorter.property)
        .set('sorterDirection', sorter.direction)
        .set('filterProperty', filter.property)
        .set('filterValue', filter.value)
        .set('filterOperator', filter.operator);

      return this.http.get<Course[]>(API_BASE_URL + `/trainings/aggregate`, {params});
    }
  }

  public getCountPages(size, sorter: Sorter, filter: Filter): any {
    if (filter === null) {
      const params = new HttpParams()
        .set('size', size.toString())
        .set('sorterProperty', sorter.property)
        .set('sorterDirection', sorter.direction)
        .set('filterProperty', null)
        .set('filterValue', null)
        .set('filterOperator', null);

      return this.http.get(API_BASE_URL + `/trainings/countPages`, {params});
    } else {
      const params = new HttpParams()
        .set('size', size.toString())
        .set('sorterProperty', sorter.property)
        .set('sorterDirection', sorter.direction)
        .set('filterProperty', filter.property)
        .set('filterValue', filter.value)
        .set('filterOperator', filter.operator);

      return this.http.get(API_BASE_URL + `/trainings/countPages`, {params});
    }
  }

  public findAllUserCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(API_BASE_URL + `/trainings/myTrainings`);
  }

  public findAllMentorCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(API_BASE_URL + `/trainings/mentorTrainings`);
  }

  public findById(id): any {
    return this.http.get(API_BASE_URL + `/trainings/${id}`);
  }

  public editCourse(id:string, name: string, description: string, price: number) {
    return this.http.put(API_BASE_URL + `/trainings/editTraining`,
      {"id": id, "name": name, "description": description, "price": price});
  }

  public addCourse(name: string, description: string, price: number, category: Category) {
    return this.http.post(API_BASE_URL + `/trainings/add`,
      {"id": "14e267e9-7725-450d-bb61-79a5f3fb78d3", "name": name, "description": description, "price": price, "category":category});
  }

  getRatingArray(course: Course) {
    let i;
    this.rating = course.trainingRate;
    let array = [];
    // fill here filling starts
    for (i = 0; i < this.rating; i++) {
      array.push(true);
    }
    // fill here outline starts
    for (i = 0; i < 5 - this.rating; i++) {
      array.push(false);
    }
    return array;
  }

  public getPurTraining() {
    return this.http.get(API_BASE_URL + `/trainings/purTraining`);
  }

  public delete(id: string) {
    return this.http.delete(API_BASE_URL + `/trainings/delete/${id}`);
  }
}
