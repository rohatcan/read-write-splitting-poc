package com.rohat.readwritesplittingpoc.configs

import net.ttddyy.dsproxy.listener.logging.CommonsLogLevel
import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
   basePackages = ["com.rohat.readwritesplittingpoc.orders"],
   entityManagerFactoryRef = "ordersEntityManagerFactory",
   transactionManagerRef = "ordersTransactionManager"
)
class DataSourceOrdersConfig {

   @Bean
   @ConfigurationProperties("spring.datasource.orders.primary")
   fun ordersPrimaryDataSource(): DataSource {
      return DataSourceBuilder.create().build()
   }

   @Bean
   @ConfigurationProperties("spring.datasource.orders.secondary")
   fun ordersSecondaryDataSource(): DataSource {
      return DataSourceBuilder.create().build()
   }

   @Primary
   @Bean
   fun ordersActualDataSource(): DataSource {
      val routingDataSource = RoutingDataSource()
      val dataSourceMap: MutableMap<Any, Any> = HashMap()
      dataSourceMap["PRIMARY"] = loggingDataSourceProxy("primary.db", ordersPrimaryDataSource())
      dataSourceMap["SECONDARY"] = loggingDataSourceProxy("secondary.db", ordersSecondaryDataSource())
      routingDataSource.setTargetDataSources(dataSourceMap)
      routingDataSource.setDefaultTargetDataSource(ordersPrimaryDataSource())

      return routingDataSource
   }

   fun loggingDataSourceProxy(loggerName: String, dataSource: DataSource): DataSource {
      return ProxyDataSourceBuilder
         .create()
         .dataSource(dataSource)
         .name("ordersActualDataSource")
         .logQueryByCommons(CommonsLogLevel.TRACE)
         .logQueryBySlf4j(SLF4JLogLevel.TRACE, loggerName)
         .build()
   }

   @Primary
   @Bean
   fun ordersEntityManagerFactory(
      ordersEntityManagerFactoryBuilder: EntityManagerFactoryBuilder,
      @Qualifier("ordersActualDataSource") ordersDataSource: DataSource
   ): LocalContainerEntityManagerFactoryBean {
      return ordersEntityManagerFactoryBuilder
         .dataSource(ordersDataSource)
         .packages("com.rohat.readwritesplittingpoc.orders")
         .persistenceUnit("ordersDataSource")
         .build()
   }

   @Primary
   @Bean
   fun ordersTransactionManager(
      @Qualifier("ordersEntityManagerFactory") ordersEntityManagerFactory: EntityManagerFactory
   ): PlatformTransactionManager {
      return JpaTransactionManager(ordersEntityManagerFactory)
   }

}
