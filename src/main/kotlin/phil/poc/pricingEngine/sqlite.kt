package phil.poc.pricingEngine.sqlite

import org.hibernate.dialect.Dialect
import org.hibernate.dialect.identity.IdentityColumnSupportImpl
import java.sql.Types

class SQLiteDialect(): Dialect() {

    init {
        registerColumnType(Types.BIT, "integer")
        registerColumnType(Types.TINYINT, "tinyint")
        registerColumnType(Types.SMALLINT, "smallint")
        registerColumnType(Types.INTEGER, "integer")
        registerColumnType(Types.BIGINT, "bigint")
        registerColumnType(Types.FLOAT, "float")
        registerColumnType(Types.REAL, "real")
        registerColumnType(Types.DOUBLE, "double")
        registerColumnType(Types.NUMERIC, "numeric")
        registerColumnType(Types.DECIMAL, "decimal")
        registerColumnType(Types.CHAR, "char")
        registerColumnType(Types.VARCHAR, "varchar")
        registerColumnType(Types.LONGVARCHAR, "longvarchar")
        registerColumnType(Types.DATE, "date")
        registerColumnType(Types.TIME, "time")
        registerColumnType(Types.TIMESTAMP, "timestamp")
        registerColumnType(Types.BINARY, "blob")
        registerColumnType(Types.VARBINARY, "blob")
        registerColumnType(Types.LONGVARBINARY, "blob")
        registerColumnType(Types.BLOB, "blob")
        registerColumnType(Types.CLOB, "clob")
        registerColumnType(Types.BOOLEAN, "integer")
    }
}

class SQLiteIdentityColumnSupport: IdentityColumnSupportImpl() {

    override fun supportsIdentityColumns() = true
    override fun getIdentitySelectString(table: String?, column: String?, type: Int) = "select last_insert_rowid()"
    override fun getIdentityColumnString(type: Int) = "integer"
}
