package ir.itstar.db

import com.typesafe.scalalogging.LazyLogging
import ir.itstar.repos._
import slick.jdbc.meta.MTable

import scala.concurrent.{ExecutionContext, Future}

object DBInitializer
    extends LazyLogging
    with NameBasicComponent
    with TitleAkaComponent
    with TitleBasicComponent
    with TitleCrewComponent
    with TitleEpisodeComponent
    with TitlePrincipalComponent
    with TitleRatingComponent {

  import DatabaseConnectorImpl.api._
  import DatabaseConnectorImpl.db

  val tables = List(
    nameBasicsTable,
    titleAkasTable,
    titleBasicsTable,
    titleCrewsTable,
    titleEpisodesTable,
    titlePrincipalsTable,
    titleRatingsTable
  )

  /**
    * check list of tables and create one that was not exists
    *
    * @param ec : [[ExecutionContext]]
    * @return
    */
  def initDb()(implicit ec: ExecutionContext): Future[List[Unit]] = {
    logger.info(s"initiating dbs on : ${DatabaseConfig.jdbcUrl}")
    val existing = db.run(MTable.getTables)
    val f = existing.flatMap(v => {
      val names = v.map(mt => mt.name.name)
      val createIfNotExist =
        tables
          .filter(table => !names.contains(table.baseTableRow.tableName))
          .map(_.schema.create)
      db.run(DBIO.sequence(createIfNotExist))
    })
    f
  }
}
