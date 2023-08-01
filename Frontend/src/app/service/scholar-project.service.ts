import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ScholarProject } from '../interface/scholar-project.interface';
import { Image } from '../interface/image.interface';
import { Reference } from '../interface/reference.interface';
import { Tag } from '../interface/tag.interface';
import { Author } from '../interface/author.interface';

@Injectable({
  providedIn: 'root'
})
export class ScholarProjectService {

  private baseUrl = 'http://localhost:8080/scholar-projects'; // Reemplaza con la URL de tu backend

  constructor(private http: HttpClient) { }

  createScholarProject(scholarProject: ScholarProject): Observable<ScholarProject> {
    return this.http.post<ScholarProject>(`${this.baseUrl}/create`, scholarProject);
  }

  updateScholarProject(id: number, scholarProject: ScholarProject): Observable<string> {
    return this.http.put<string>(`${this.baseUrl}/update/${id}`, scholarProject);
  }

  deleteScholarProject(id: number): Observable<string> {
    return this.http.delete<string>(`${this.baseUrl}/delete/${id}`);
  }

  getScholarProjectById(id: number): Observable<ScholarProject> {
    return this.http.get<ScholarProject>(`${this.baseUrl}/${id}`);
  }

  getAllScholarProjects(): Observable<ScholarProject[]> {
    return this.http.get<ScholarProject[]>(`${this.baseUrl}/`);
  }

  searchScholarProjectsByTitle(title: string): Observable<ScholarProject[]> {
    return this.http.get<ScholarProject[]>(`${this.baseUrl}/search/title?title=${title}`);
  }

  searchScholarProjectsByTag(tagName: string): Observable<ScholarProject[]> {
    return this.http.get<ScholarProject[]>(`${this.baseUrl}/search/tag?tagName=${tagName}`);
  }

  addImagesToScholarProject(id: number, images: Image[]): Observable<string> {
    return this.http.put<string>(`${this.baseUrl}/${id}/add-images`, images);
  }

  addReferencesToScholarProject(id: number, references: Reference[]): Observable<string> {
    return this.http.put<string>(`${this.baseUrl}/${id}/add-references`, references);
  }

  addTagsToScholarProject(id: number, tags: Tag[]): Observable<string> {
    return this.http.put<string>(`${this.baseUrl}/${id}/add-tags`, tags);
  }

  addAuthorsToScholarProject(id: number, authors: Author[]): Observable<string> {
    return this.http.put<string>(`${this.baseUrl}/${id}/add-authors`, authors);
  }

}
