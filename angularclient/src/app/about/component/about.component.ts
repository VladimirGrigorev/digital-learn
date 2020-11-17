import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent implements OnInit {

  ngOnInit(): void {
    throw new Error("Method not implemented.");
  }

  constructor(
    private router: Router,
    private toastr: ToastrService,
  ) {}
}
