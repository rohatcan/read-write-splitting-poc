package com.rohat.readwritesplittingpoc.products

import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Product, Long>
