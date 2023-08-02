import { Component, ElementRef, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  isNavbarActive = false;

  constructor(private router: Router, 
              private route: ActivatedRoute, 
              private elementRef: ElementRef) { }

  isRouteActive(path: string): boolean {
    return this.router.url === path;
  }

  toggleNavbar() {
    this.isNavbarActive = !this.isNavbarActive;
    
    if (this.isNavbarActive) {
      document.addEventListener('click', this.closeNavbar.bind(this));
    } else {
      document.removeEventListener('click', this.closeNavbar.bind(this));
    }
  }
  
  closeNavbar(event: Event) {
    if (this.isNavbarActive && !this.elementRef.nativeElement.contains(event.target)) {
      this.isNavbarActive = false;
      document.removeEventListener('click', this.closeNavbar.bind(this));
    }
  }
}
