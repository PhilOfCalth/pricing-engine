//package phil.poc.pricingEngineService.sqlite
//
//import org.hibernate.MappingException
//import org.hibernate.dialect.Dialect
//import org.hibernate.dialect.identity.IdentityColumnSupport
//import org.hibernate.dialect.identity.IdentityColumnSupportImpl
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.context.properties.ConfigurationProperties
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Primary
//import org.springframework.core.env.Environment
//import org.springframework.jdbc.datasource.DriverManagerDataSource
//import java.sql.Types
//import javax.sql.DataSource
//
//
//class SQLiteDialect : Dialect() {
//
//    @Autowired
//    var env: Environment? = null
//
//    init {
//        registerColumnType(Types.BIT, "integer")
//        registerColumnType(Types.TINYINT, "tinyint")
//        registerColumnType(Types.SMALLINT, "smallint")
//        registerColumnType(Types.INTEGER, "integer")
//        registerColumnType(Types.CHAR, "character")
//        registerColumnType(Types.VARCHAR, "varchar")
//        registerColumnType(Types.DOUBLE, "double")
//        registerColumnType(Types.BOOLEAN, "integer")
//        registerColumnType(Types.DATE, "date")
//    }
//
//    override fun  getIdentityColumnSupport(): IdentityColumnSupport{
//        return SQLiteIdentityColumnSupport()
//    }
//
//    override fun hasAlterTable(): Boolean {
//        return false
//    }
//
//    override fun dropConstraints(): Boolean {
//        return false
//    }
//
//    override fun getDropForeignKeyString(): String {
//        return ""
//    }
//
//    override fun getAddForeignKeyConstraintString(cn: String?, fk: Array<String>, t: String?,
//                                                  pk: Array<String>, rpk: Boolean):
//                                                String {
//        return ""
//    }
//
//    override fun getAddPrimaryKeyConstraintString(constraintName: String?): String {
//        return ""
//    }
//
//    @Primary
//    @Bean(name = ["dataSource"])
//    @ConfigurationProperties(prefix = "spring.datasource")
//    fun dataSource(): DataSource {
//        val dataSource = DriverManagerDataSource()
//        dataSource.setDriverClassName("org.sqlite.JDBC")
//        dataSource.url = "jdbc:sqlite:peDB.db"
////        dataSource.username =
////        dataSource.password =
//        return dataSource
//    }
//}
//
//class SQLiteIdentityColumnSupport : IdentityColumnSupportImpl() {
//
//    override fun supportsIdentityColumns(): Boolean {
//        return true
//    }
//
//    @Throws(MappingException::class)
//    override fun getIdentitySelectString(table: String?, column: String?, type: Int): String {
//        return "select last_insert_rowid()"
//    }
//
//    @Throws(MappingException::class)
//    override fun getIdentityColumnString(type: Int): String {
//        return "integer"
//    }
//}