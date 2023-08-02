import { Component, OnInit, ElementRef, Renderer2 } from '@angular/core';
import { Router } from '@angular/router';
import { faGithub, faLinkedinIn } from '@fortawesome/free-brands-svg-icons';
import { faEnvelope } from '@fortawesome/free-regular-svg-icons';



@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  faGithub = faGithub
  linkedin = faLinkedinIn;
  email = faEnvelope

  leyends: string[] = ['Mathematician', 'Computer scientist', 'Software Developer'];
  currentLeyend: string = '';
  currentIndex: number = 0;
  typingSpeed: number = 100;
  eraseSpeed: number = 50;
  delayAfterTyping: number = 1000;

  constructor(private router: Router) { }

  ngOnInit() {
    this.startTypingEffect();
  }

  /**
   * Starts the typing effect
   */
  startTypingEffect() {
    const currentLeyend = this.leyends[this.currentIndex];
    if (this.currentLeyend.length < currentLeyend.length) {
      this.currentLeyend += currentLeyend.charAt(this.currentLeyend.length);
      setTimeout(() => {
        this.startTypingEffect();
      }, this.typingSpeed);
    } else {
      setTimeout(() => {
        this.clearTypingEffect();
      }, this.delayAfterTyping);
    }
  }

  /**
   * Clears the typing effect.
   */
  clearTypingEffect() {
    if (this.currentLeyend.length > 0) {
      this.currentLeyend = this.currentLeyend.slice(0, -1);
      setTimeout(() => {
        this.clearTypingEffect();
      }, this.eraseSpeed);
    } else {
      this.currentIndex = (this.currentIndex + 1) % this.leyends.length;
      setTimeout(() => {
        this.startTypingEffect();
      }, this.typingSpeed);
    }
  }
}
