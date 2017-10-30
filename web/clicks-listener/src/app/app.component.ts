import { Component } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  data: any = null;

  constructor(private _http: Http) {
    this.getClicks();
  }

  private getClicks() {
    return this._http.get('https://request-counter.appspot.com/api/getclicks')
      .map((res: Response) => res.json())
      .subscribe(data => {
        this.data = data;
        console.log(this.data);
        console.log(this.data.amount);
      });
  }}

