package com.rohat.readwritesplittingpoc.configs

import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.stereotype.Component
import org.springframework.transaction.TransactionDefinition
import org.springframework.transaction.support.TransactionSynchronizationManager
import javax.persistence.EntityManagerFactory

@Component
class ProxyJpaTransactionManager(
   emf: EntityManagerFactory
) : JpaTransactionManager(emf) {


   override fun doBegin(transaction: Any, definition: TransactionDefinition) {
      TransactionSynchronizationManager.setCurrentTransactionReadOnly(definition.isReadOnly)
      super.doBegin(transaction, definition)
   }
}
