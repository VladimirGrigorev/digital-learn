import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {CartService} from "../service/cart.service";
import {Cart} from "../model/cart";
import {CourseService} from "../../courses/service/course.service";
import {Order} from "../model/order";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cart: Cart;
  totalPrice: any;
  isEnable: boolean;
  order: Order;

  constructor(
    private router: Router,
    private toastr: ToastrService,
    private cartService: CartService,
    private courseService: CourseService
  ) {}

  ngOnInit() {
    this.cartService.findTrainings().subscribe(data => {
      this.cart = data;
    });
    this.cartService.getTotalPrice().subscribe(data => {
      this.totalPrice = data;
    });
    this.isEnable = true;
  }

  reloadComponent() {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate(['/cart']);
  }

  buy() {
    this.order = new Order();
    this.order.orderTotal = this.totalPrice;
    this.order.trainings = this.cart.trainings;
    this.cartService.purchase(this.order).subscribe(
      data => {
        this.toastr.success("Вы успешно приобрели тренинг.");
        this.router.navigate(['/my-courses']);
      }, error => {
        console.log(error);
        //this.toastr.error("Вы уже приобрели тренинг.");
        this.router.navigate(['/my-courses']);
      }
    )
  }

  clear() {
    this.cartService.clear().subscribe(
      data => {
        this.toastr.success("You're successfully clear cart.");
        this.reloadComponent();
      }, error => {
        console.log(error);
      });
  }

  click(id: string, isCart: boolean) {
    if  (isCart && this.isEnable){
      this.router.navigate(['/course', id]);
    }
    else {
      this.isEnable = true;
    }
    if (!isCart){
      this.isEnable = false;
      this.cartService.deleteTraining(id).subscribe(
        data => {
          this.toastr.success("Вы успешно удалили курс из корзины");
          this.reloadComponent();
        }, error => {
          console.log(error);
        });
    }
  }
}
