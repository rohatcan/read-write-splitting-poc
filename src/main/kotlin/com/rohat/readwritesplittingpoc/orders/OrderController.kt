package com.rohat.readwritesplittingpoc.orders

import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController(
   private val orderRepository: OrderRepository,
   private val orderService: OrderService,
) {

   fun incrementBasketSize() {
      val order = orderRepository.findByIdOrNull(1L) ?: run {
         val newOrder = Order(1L, "Order 1", "Description 1", 0)
         orderRepository.save(newOrder)
         newOrder
      }
      order.basketSize += 1
      orderRepository.save(order)
   }

   @GetMapping("{id}")
   fun getOrder(@PathVariable id: Long): Order {
      return orderService.getOrder(id)
   }

}
