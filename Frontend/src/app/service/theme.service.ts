import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ThemeService {
  private isDarkTheme = new BehaviorSubject<boolean>(false);

  constructor() {
    const storedTheme = localStorage.getItem('darkTheme');
    if (storedTheme !== null) {
      this.isDarkTheme.next(storedTheme === 'true');
    }
  }

  toggleTheme() {
    this.isDarkTheme.next(!this.isDarkTheme.value);
    localStorage.setItem('darkTheme', this.isDarkTheme.value.toString());
  }

  getTheme() {
    return this.isDarkTheme.asObservable();
  }
}
