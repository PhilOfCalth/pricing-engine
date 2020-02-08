package phil.poc.pricingEngine.config

import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.validation.Validator
import phil.poc.pricingEngine.projections.CustomerProduct
import java.util.*
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(basePackages = ["phil.poc.pricingEngine.repositories"])
class DbConfig{

    @Autowired
    private lateinit var env: Environment

    @Bean
    fun dataSource(): DataSource{
        val dataSource = DriverManagerDataSource()
        val driverClassNameVal: String = env.getProperty("driverClassName")?: ""
        dataSource.setDriverClassName(driverClassNameVal)
        dataSource.url = env.getProperty("url")
        dataSource.username = env.getProperty("user")
        dataSource.password = env.getProperty("password")
        return dataSource
    }

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean{
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource()
        em.setPackagesToScan("phil.poc.pricingEngine.models")
        em.jpaVendorAdapter = HibernateJpaVendorAdapter()
        em.setJpaProperties(additionalProperties())
        return em
    }

    fun additionalProperties():Properties{
        val hibernateProperties = Properties()
        if(env.getProperty("hibernate.hbm2ddl.auto") != null){
            hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"))
        }
        if (env.getProperty("hibernate.dialect") != null) {
            hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"))
        }
        if (env.getProperty("hibernate.show_sql") != null) {
            hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"))
        }
        return hibernateProperties
    }

}

@Configuration
@Profile("sqlite")
@PropertySource("classpath:persistence-sqlite.properties")
class SqliteConfig

@Configuration
class RestConfig: RepositoryRestConfigurer{

    override fun configureRepositoryRestConfiguration(repositoryRestConfiguration: RepositoryRestConfiguration) {
        repositoryRestConfiguration.projectionConfiguration.addProjection(CustomerProduct::class.java)
    }
}

@Configuration
class ValidatorEventRegister : InitializingBean {

    @Autowired
    internal lateinit var validatingRepositoryEventListener: ValidatingRepositoryEventListener

    @Autowired
    private lateinit var validators: Map<String, Validator>

    @Throws(Exception::class)
    override fun afterPropertiesSet() {
        val events = listOf(
            "beforeCreate",
            "afterCreate",
            "beforeSave",
            "afterSave",
            "beforeLinkSave",
            "afterLinkSave",
            "beforeDelete",
            "afterDelete"
        )

        for ((key, value) in validators) {
            events.stream().filter { p -> key.startsWith(p) }.findFirst().ifPresent { p ->
                validatingRepositoryEventListener.addValidator(p, value)
            }
        }
    }
}
