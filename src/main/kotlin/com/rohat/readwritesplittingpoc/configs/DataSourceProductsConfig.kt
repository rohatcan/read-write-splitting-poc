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
   basePackages = ["com.rohat.readwritesplittingpoc.products"],
   entityManagerFactoryRef = "productsEntityManagerFactory",
   transactionManagerRef = "productsTransactionManager"
)
class DataSourceProductsConfig {

   @Bean
   @ConfigurationProperties("spring.datasource.products")
   fun productsDataSourceProperties(): DataSourceProperties {
      return DataSourceProperties()
   }

   @Bean
   fun productsDataSource(): DataSource {
      return productsDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource::class.java).build()
   }

   @Bean
   fun productsEntityManagerFactory(
      productsEntityManagerFactoryBuilder: EntityManagerFactoryBuilder,
      @Qualifier("productsDataSource") productsDataSource: DataSource
   ): LocalContainerEntityManagerFactoryBean {
      return productsEntityManagerFactoryBuilder
         .dataSource(productsDataSource)
         .packages("com.rohat.readwritesplittingpoc.products")
         .persistenceUnit("productsDataSource")
         .build()
   }

   @Bean
   fun productsTransactionManager(
      @Qualifier("productsEntityManagerFactory") productsEntityManagerFactory: EntityManagerFactory
   ): PlatformTransactionManager {
      return JpaTransactionManager(productsEntityManagerFactory)
   }

}


