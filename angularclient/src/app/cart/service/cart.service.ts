import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {API_BASE_URL} from "../../constants/constants";
import {Cart} from "../model/cart";
import {Order} from "../model/order";

@Injectable()
export class CartService {

  constructor(private http: HttpClient) { }

  public purchase(order: Order) {
    return this.http.post(API_BASE_URL + `/orders/add`, {
      "trainings": order.trainings,
      "orderTotal": order.orderTotal
    });
  }

  public findTrainings(): Observable<Cart> {
    return this.http.get<Cart>(API_BASE_URL + `/cart`);
  }

  public toCartTraining(id: string) {
    return this.http.post<any>(API_BASE_URL + `/cart`, id);
  }

  public deleteTraining(id: string) {
    return this.http.delete(API_BASE_URL + `/cart/${id}`);
  }

  public getTotalPrice() {
    return this.http.get(API_BASE_URL + `/cart/totalPrice`);
  }

  public clear() {
    return this.http.delete(API_BASE_URL + `/cart/deleteAll`);
  }
}
