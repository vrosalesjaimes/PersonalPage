import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-load-protector',
  templateUrl: './load-protector.component.html',
  styleUrls: ['./load-protector.component.css']
})
export class LoadProtectorComponent implements OnInit{
  protected typedText: string = '';
  private fullText: string = 'Welcome to my website...';
  protected loading = true;

  constructor(){}

  ngOnInit(): void {
    this.typeText();
    setTimeout(() => {
      this.hideLoader();
    }, 2000);
  }

  typeText() {
    let index = 0;
    const typingInterval = setInterval(() => {
      if (index === this.fullText.length) {
        clearInterval(typingInterval);
      } else {
        this.typedText += this.fullText[index];
        index++;
      }
    }, 100);
  }

  hideLoader() {
    const protector = document.querySelector('.protector') as HTMLElement;
    protector.classList.add('hide');
    setTimeout(() => {
      this.loading = false;
    }, 4000); 
  }
}
