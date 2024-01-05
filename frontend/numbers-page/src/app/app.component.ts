import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { HttpClientModule } from "@angular/common/http";
import {PrimeCheckerComponent} from "../components/prime-checker/prime-checker.component";
import {CachedPrimesComponent} from "../components/cached-primes/cached-primes.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, HttpClientModule, RouterOutlet, PrimeCheckerComponent, CachedPrimesComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'numbers-page';
}
