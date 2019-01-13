package ir.itstar.db

import com.typesafe.scalalogging.LazyLogging
import slick.jdbc.PostgresProfile

object DatabaseConnectorImpl extends PostgresProfile with LazyLogging {

  import api._

  val db: backend.DatabaseDef = Database.forConfig("db")
  db.createSession()

  def closeDB(): Unit = {
    logger.info("closing db connections")
    db.close()
  }

}
