import { Injectable, Renderer2 } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ThemeToggleService {

  isDarkTheme: boolean = false;

  toggleTheme(renderer: Renderer2): void {
    this.isDarkTheme = !this.isDarkTheme;
    
    const themeContainers = document.querySelectorAll('.theme_container');
    themeContainers.forEach(container => {
      renderer.removeClass(container, this.isDarkTheme ? 'light' : 'dark');
      renderer.addClass(container, this.isDarkTheme ? 'dark' : 'light');
    });

    localStorage.setItem('theme', this.isDarkTheme ? 'dark' : 'light');
  }

  checkedTheme(renderer: Renderer2): void {
    const storedTheme = localStorage.getItem('theme');
    if (storedTheme) {
      this.isDarkTheme = storedTheme === 'dark';
      const themeContainers = document.querySelectorAll('.theme_container');
      themeContainers.forEach(container => {
        renderer.removeClass(container, this.isDarkTheme ? 'light' : 'dark');
        renderer.addClass(container, this.isDarkTheme ? 'dark' : 'light');
      });
    }
  }
}
