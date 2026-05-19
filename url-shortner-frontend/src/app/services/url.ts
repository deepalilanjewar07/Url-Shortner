import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UrlService {
  private apiUrl = 'http://localhost:8080/api/create';

  constructor(private http: HttpClient) {}

  createShortUrl(data: any) {
    return this.http.post(this.apiUrl, data);
  }
}
