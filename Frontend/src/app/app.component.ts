import { Component, HostBinding, Inject } from '@angular/core';
import { ThemeService } from './service/theme.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Frontend';

  constructor(private themeService: ThemeService) {
    this.themeService.getTheme().subscribe(isDarkTheme => {
      const theme = isDarkTheme ? 'dark-theme' : 'light-theme';
      document.body.className = theme;
    });
  }
}
