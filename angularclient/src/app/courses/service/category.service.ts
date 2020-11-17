import { Injectable } from '@angular/core';
import {API_BASE_URL} from "../../constants/constants";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Category} from "../model/category";

@Injectable()
export class CategoryService {

  constructor(private http: HttpClient) { }

  public findAll(): Observable<Category[]> {
    return this.http.get<Category[]>(API_BASE_URL + `/categories`);
  }
}
