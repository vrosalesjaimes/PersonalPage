import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Image } from '../interface/image';
import { Observable } from 'rxjs';
import { IMAGE_URL } from '../routes.constants';

@Injectable({
  providedIn: 'root'
})
export class ImageService {


  constructor(private http: HttpClient) { }

  createImage(image: Image): Observable<any> {
    return this.http.post(`${IMAGE_URL}/create`, image);
  }

  deleteImage(id: number): Observable<any> {
    return this.http.delete(`${IMAGE_URL}/delete/${id}`);
  }
}
