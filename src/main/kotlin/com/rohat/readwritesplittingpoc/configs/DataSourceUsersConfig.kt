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
   basePackages = ["com.rohat.readwritesplittingpoc.users"],
   entityManagerFactoryRef = "usersEntityManagerFactory",
   transactionManagerRef = "usersTransactionManager"
)
class DataSourceUsersConfig {

   @Bean
   @ConfigurationProperties("spring.datasource.users")
   fun usersDataSourceProperties(): DataSourceProperties {
      return DataSourceProperties()
   }

   @Bean
   fun usersDataSource(): DataSource {
      return usersDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource::class.java).build()
   }

   @Bean
   fun usersEntityManagerFactory(
      usersEntityManagerFactoryBuilder: EntityManagerFactoryBuilder,
      @Qualifier("usersDataSource") usersDataSource: DataSource
   ): LocalContainerEntityManagerFactoryBean {
      return usersEntityManagerFactoryBuilder
         .dataSource(usersDataSource)
         .packages("com.rohat.readwritesplittingpoc.users")
         .persistenceUnit("usersDataSource")
         .build()
   }

   @Bean
   fun usersTransactionManager(
      @Qualifier("usersEntityManagerFactory") usersEntityManagerFactory: EntityManagerFactory
   ): PlatformTransactionManager {
      return JpaTransactionManager(usersEntityManagerFactory)
   }

}
