import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { About } from '../interface/about.interface';
import { Image } from '../interface/image.interface';
import { HttpClient } from '@angular/common/http';
import { ABOUT_URL } from '../routes.constants';

@Injectable({
  providedIn: 'root'
})
export class AboutService {

  constructor(private http:HttpClient) { }

  createAbout(about: About): Observable<any> {
    return this.http.post(`${ABOUT_URL}/create`, about);
  }

  updateAbout(id: number, about: About): Observable<any> {
    return this.http.put(`${ABOUT_URL}/update/${id}`, about);
  }

  deleteAbout(id: number): Observable<any> {
    return this.http.delete(`${ABOUT_URL}/delete/${id}`);
  }

  addImages(id: number, images: Image[]): Observable<any> {
    return this.http.put(`${ABOUT_URL}/${id}/add-images`, images);
  }

}
