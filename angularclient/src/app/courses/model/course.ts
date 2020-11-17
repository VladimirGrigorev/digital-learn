import {UserInfo} from "../../users/model/user-info";
import {Category} from "./category";
import {File} from "./file";

export class Course {
  id: string;
  name: string;
  price: number;
  description: string;
  categories: Category[];
  trainingRate: number;
  user: UserInfo;
  content: File[];
  progress: number;
  isDelete: boolean;
  imgUrl: string;
}
