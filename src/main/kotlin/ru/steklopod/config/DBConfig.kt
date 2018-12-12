package ru.steklopod.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@Configuration
@EntityScan(basePackages = ["ru.steklopod.model"])
@EnableJpaRepositories(basePackages = ["ru.steklopod.repositories"])
//@EnableTransactionManagement
class DBConfig


//{
//    @Value("\${spring.jpa.hibernate.ddl-auto}")
//    private lateinit var ddlAuto: String
//
//    private val PACKAGES_TO_SCAN = "ru.steklopod.model"
//
//    @Bean
//    @ConfigurationProperties("spring.datasource")
//    fun dataSource(): DataSource {
//        return DataSourceBuilder.create().build()
//    }
//
//    private fun jpaProperties(): Map<String, Any> {
//        val props = HashMap<String, Any>()
//        props["hibernate.connection.shutdown"] = "true"
//        props["hibernate.proc.param_null_passing"] = "true"
//        props["hibernate.classloading.use_current_tccl_as_parent"] = "false"
//        return props
//    }
//
//    @Bean
//    fun entityManagerFactory(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean {
//        return builder
//            .dataSource(dataSource())
//            .packages(PACKAGES_TO_SCAN)
//            .persistenceUnit("___gpb___")
//            .properties(jpaProperties())
//            .build()
//    }
//
//    @Bean
//    fun transactionManager(@Qualifier("entityManagerFactory") entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
//        return JpaTransactionManager(entityManagerFactory)
//    }
//
//    @Bean
//    fun jdbcTemplate(): JdbcTemplate {
//        return JdbcTemplate(dataSource())
//    }
//}