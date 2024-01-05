import { Injectable } from '@angular/core';
import {NumberResponse} from "../models/numberResponse";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import {CachedPrimesResponse} from "../models/cachedPrimesResponse";

@Injectable({
  providedIn: 'root',
})
export class PaymentService {
  cachedPrimes: number[] = []

  constructor(private http: HttpClient) { }

  checkIfPrime(num: number): Observable<NumberResponse> {
    const url = "http://localhost:8080/is_prime"
    const payload = {number: num}

    return this.http.post<NumberResponse>(url, payload);
  }

  refreshCache() {
    const url = "http://localhost:8080/cached_primes"

    this.http.get<CachedPrimesResponse>(url).subscribe(resp => {
      this.cachedPrimes = resp.values
    })
  }
}
