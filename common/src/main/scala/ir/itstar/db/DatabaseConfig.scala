package ir.itstar.db
import com.typesafe.config.{Config, ConfigFactory}

object DatabaseConfig {
  val config: Config       = ConfigFactory.load()
  val databaseName: String = config.getString("db.properties.databaseName")
  val jdbcUrl: String      = s"jdbc:postgresql://127.0.0.1:5432/$databaseName"
  val username: String     = config.getString("db.properties.user")
  val password: String     = config.getString("db.properties.password")
}
