import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Education } from '../interface/education.interface';
import { Observable } from 'rxjs';
import { EDUCATION_URL } from '../routes.constants';



@Injectable({
  providedIn: 'root'
})
export class EducationService {

  constructor(private http: HttpClient) { }

  createEducation(education: Education): Observable<any> {
    return this.http.post(`${EDUCATION_URL}/create`, education);
  }

  updateEducation(id: number, education: Education): Observable<any> {
    return this.http.put(`${EDUCATION_URL}/${id}`, education);
  }

  deleteEducation(id: number): Observable<any> {
    return this.http.delete(`${EDUCATION_URL}/${id}`);
  }

  getAll(): Observable<Education[]> {
    return this.http.get<Education[]>(`${EDUCATION_URL}/`);
  }
}
