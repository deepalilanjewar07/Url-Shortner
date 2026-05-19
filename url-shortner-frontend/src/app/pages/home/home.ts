import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UrlService } from '../../services/url';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  url = '';
  shortUrl = '';
  copied = false;
  loading = false;

  urlhistory: { original: string; short: string }[] = [];

  constructor(private urlService: UrlService, private cdr: ChangeDetectorRef) {}

  shortenUrl() {
    console.log('URL BEFORE CALL:', this.url);

    this.loading = true;

    this.urlService.createShortUrl({ url: this.url }).subscribe({
      next: (response: any) => {
        console.log('RESPONSE:', response);
        console.log("SHORT LINK:", response.shortLink);

        const generatedShortUrl = 'http://localhost:8080/api/' + response.shortLink;
        this.shortUrl=generatedShortUrl;
        // history add (NEW FEATURE)
        this.urlhistory.unshift({
          original: this.url,
          short: this.shortUrl,
        });

        this.loading = false;


        this.cdr.detectChanges();

      },
      error: (err) => {
        console.log(err);
        this.loading = false;
      },
    });
  }

  copy(url: string) {
    navigator.clipboard.writeText(url);
    this.copied = true;

    setTimeout(() => {
      this.copied = false;
    }, 1500);
  }

  deleteFromHistory(short: string) {
    this.urlhistory = this.urlhistory.filter((item) => item.short !== short);
  }
}
