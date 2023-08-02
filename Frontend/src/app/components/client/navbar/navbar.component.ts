import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  isNavbarActive = false;

  constructor(private router: Router, private route: ActivatedRoute) { }

  isRouteActive(path: string): boolean {
    return this.router.url === path;
  }

  toggleNavbar() {
    this.isNavbarActive = !this.isNavbarActive;
  }
}
