package ir.itstar.db

import com.github.tminglei.slickpg._
import com.github.tminglei.slickpg.json.PgJsonExtensions
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}

sealed trait DatabaseConnector

object DatabaseConnector extends DatabaseConnector with PostgresProfiler {

  import DatabaseConfig._

  private val hikariDataSource = {
    val hikariConfig = new HikariConfig()
    hikariConfig.setJdbcUrl(jdbcUrl)
    hikariConfig.setUsername(username)
    hikariConfig.setPassword(password)

    new HikariDataSource(hikariConfig)
  }

  import api._

  val db: backend.DatabaseDef = Database.forDataSource(hikariDataSource, None)
  db.createSession()

  def closeDB(): Unit = {
    logger.info("closing db connections")
    db.close()
  }

}

trait PostgresProfiler extends ExPostgresProfile with PgArraySupport with PgSprayJsonSupport {

  override val pgjson = "jsonb"

  override val api: API = new API {}

  trait API extends super.API with ArrayImplicits with JsonImplicits

}

object PostgresProfiler extends PostgresProfiler with PgJsonExtensions
