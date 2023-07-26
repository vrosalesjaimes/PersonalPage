import { Component, HostBinding, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Frontend';

  @HostBinding('class') componentCssClass: any;

  constructor(@Inject(DOCUMENT) private document: Document) {}

  setTheme(theme: string) {
    this.componentCssClass = theme;
    this.document.body.classList.forEach((className) => {
      if (className.includes('-theme')) {
        this.document.body.classList.remove(className);
      }
    });
    this.document.body.classList.add(theme);
  }
}
