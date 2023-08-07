import { Component, Renderer2 } from '@angular/core';
import { ThemeToggleService } from 'src/app/service/theme-toggle.service';

@Component({
  selector: 'app-toggle-theme',
  templateUrl: './toggle-theme.component.html',
  styleUrls: ['./toggle-theme.component.css']
})
export class ToggleThemeComponent {
  constructor(protected themeService: ThemeToggleService,
              protected renderer: Renderer2){}

  toggleTheme(): void{
    this.themeService.toggleTheme(this.renderer);
  }
}
