package com.rohat.readwritesplittingpoc.configs

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
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
   @ConfigurationProperties("spring.datasource.orders")
   fun ordersDataSourceProperties(): DataSourceProperties {
      return DataSourceProperties()
   }

   @Bean
   fun ordersDataSource(): DataSource {
      return ordersDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource::class.java).build()
   }

   @Bean
   fun ordersEntityManagerFactory(
      ordersEntityManagerFactoryBuilder: EntityManagerFactoryBuilder,
      ordersDataSource: DataSource
   ): LocalContainerEntityManagerFactoryBean {
      return ordersEntityManagerFactoryBuilder
         .dataSource(ordersDataSource)
         .packages("com.rohat.readwritesplittingpoc.orders")
         .persistenceUnit("ordersDataSource")
         .build()
   }

   @Bean
   fun ordersTransactionManager(
      @Qualifier("ordersEntityManagerFactory") ordersEntityManagerFactory: EntityManagerFactory
   ): PlatformTransactionManager {
      return JpaTransactionManager(ordersEntityManagerFactory)
   }


}
