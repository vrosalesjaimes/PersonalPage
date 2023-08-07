import { Component, OnInit, Renderer2 } from '@angular/core';
import { ThemeToggleService } from './service/theme-toggle.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Frontend';

  constructor(private themeService: ThemeToggleService,
              private renderer :Renderer2) {} 
  ngOnInit(): void {
    this.themeService.checkedTheme(this.renderer);
  }
}
