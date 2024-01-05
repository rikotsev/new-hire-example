import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {PaymentService} from "../../services/payment.service";

@Component({
  selector: 'app-prime-checker',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './prime-checker.component.html',
  styleUrl: './prime-checker.component.scss'
})
export class PrimeCheckerComponent {
  userInput: number = 1
  isPrime: boolean = false
  isCached: boolean = false
  outputNumber: number = 0

  constructor(private paymentService: PaymentService) {
  }

  check() {
    this.paymentService.checkIfPrime(this.userInput).subscribe(
      resp => {
        this.outputNumber = resp.number
        this.isPrime = resp.isPrime
        this.isCached = resp.isCached
        this.paymentService.refreshCache()
      }
    )
  }
}
