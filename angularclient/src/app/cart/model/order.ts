import {Course} from "../../courses/model/course";
import {UserInfo} from "../../users/model/user-info";

export class Order {
  trainings: Course[];
  orderTotal: any;
}
