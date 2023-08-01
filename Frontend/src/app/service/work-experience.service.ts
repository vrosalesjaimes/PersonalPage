import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { WorkExperience } from '../interface/work-experience';


@Injectable({
  providedIn: 'root'
})
export class WorkExperienceService {

  private baseUrl = 'http://localhost:8080/work-experiences'; // Reemplaza con la URL de tu backend

  constructor(private http: HttpClient) { }

  createWorkExperience(workExperience: WorkExperience): Observable<WorkExperience> {
    return this.http.post<WorkExperience>(`${this.baseUrl}/create`, workExperience);
  }

  updateWorkExperience(id: number, workExperience: WorkExperience): Observable<string> {
    return this.http.put<string>(`${this.baseUrl}/update/${id}`, workExperience);
  }

  deleteWorkExperience(id: number): Observable<string> {
    return this.http.delete<string>(`${this.baseUrl}/delete/${id}`);
  }

  getAllWorkExperiences(): Observable<WorkExperience[]> {
    return this.http.get<WorkExperience[]>(`${this.baseUrl}/`);
  }
}
