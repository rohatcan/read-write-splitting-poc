package com.rohat.readwritesplittingpoc.orders

import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long>
