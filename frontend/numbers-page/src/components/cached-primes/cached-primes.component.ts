import { Component } from '@angular/core';
import {NgForOf} from "@angular/common";
import {PaymentService} from "../../services/payment.service";

@Component({
  selector: 'app-cached-primes',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './cached-primes.component.html',
  styleUrl: './cached-primes.component.scss'
})
export class CachedPrimesComponent {
  paymentService: PaymentService;

  constructor(paymentService: PaymentService) {
    this.paymentService = paymentService;
  }

}
