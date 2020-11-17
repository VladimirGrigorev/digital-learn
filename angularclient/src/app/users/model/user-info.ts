import {Cart} from "../../cart/model/cart";

export class UserInfo {
  id: string;
  username: string;
  email: string;
  password: string;
  roles: Array<any>;
  files: string[];
  isblock: boolean;
  cart: Cart;
  purchasedTrainings: any[];
}
