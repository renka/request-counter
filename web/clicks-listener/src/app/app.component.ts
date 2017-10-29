import { Component } from '@angular/core';

function getClicks() {
  fetch('https://request-counter.appspot.com/api/getclicks')
    .then((response) => response.json())
    .then((responseJson) => {
      return responseJson;
    })
    .catch((error) => {
      console.error(error);
    });
  return -1;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Demo';
  clicks = getClicks();
}
