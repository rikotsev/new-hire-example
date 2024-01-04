package com.cellpointdigital.example.system.cache.repository

import jakarta.inject.Singleton

@Singleton
class NumbersRepositoryImpl : NumbersRepository {

    private val numbers: MutableSet<Long> = HashSet()
    override fun contains(number: Long): Boolean {
        return numbers.contains(number)
    }

    override fun add(number: Long) {
        numbers.add(number)
    }

    override fun all(): Set<Long> = numbers
}