package com.cellpointdigital.example.system.cache.repository

interface NumbersRepository {
    fun contains(number: Long): Boolean
    fun add(number: Long)
}