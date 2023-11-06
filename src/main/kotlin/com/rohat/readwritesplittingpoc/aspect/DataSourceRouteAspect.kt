package com.rohat.readwritesplittingpoc.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.support.TransactionSynchronizationManager

@Aspect
@Component
@Order(0)
class DataSourceRouteAspect {

   @Around("@annotation(transactional)")
   fun proceed(proceedingJoinPoint: ProceedingJoinPoint, transactional: Transactional) : Any {
      TransactionSynchronizationManager.setCurrentTransactionReadOnly(transactional.readOnly)
      return proceedingJoinPoint.proceed()
   }
}
