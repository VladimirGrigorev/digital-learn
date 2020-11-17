import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import { AuthService } from '../../service/auth.service';
import { Subscription } from "rxjs";

@Component({
  selector: 'app-redirect-handler',
  templateUrl: './redirect-handler.component.html',
  styleUrls: ['./redirect-handler.component.css']
})
export class RedirectHandlerComponent implements OnInit {

  token: string;
  private sub: Subscription;
  private querySubscription: Subscription;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.token = params['token'];
      this.querySubscription = this.route.queryParams.subscribe(
        (queryParam: any) => {
          this.token = queryParam['token'];
          this.authService.loginWithToken(this.token);
          this.router.navigate(['/home']);
        }
      );
    });
  }

}
