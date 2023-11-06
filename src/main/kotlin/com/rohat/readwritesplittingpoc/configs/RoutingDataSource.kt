package com.rohat.readwritesplittingpoc.configs

import org.springframework.core.annotation.Order
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.transaction.support.TransactionSynchronizationManager

class RoutingDataSource : AbstractRoutingDataSource() {
   @Order
   override fun determineCurrentLookupKey(): Any {
      return if (TransactionSynchronizationManager.isCurrentTransactionReadOnly()) "SECONDARY" else "PRIMARY"
   }
}
