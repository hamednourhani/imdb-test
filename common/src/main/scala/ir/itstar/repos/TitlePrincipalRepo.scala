package ir.itstar.repos

import ir.itstar.db.DatabaseConnectorImpl
import ir.itstar.models.TitlePrincipal
import slick.lifted.ProvenShape

trait TitlePrincipalRepo {}

object TitlePrincipalRepoImpl extends TitlePrincipalRepo with TitlePrincipalComponent {}

trait TitlePrincipalComponent {

  import DatabaseConnectorImpl.profile.api._

  private[TitlePrincipalComponent] final class TitlePrincipalTable(tag: Tag)
      extends Table[TitlePrincipal](tag, "title_principals") {

    def tconst     = column[String]("tconst")
    def ordering   = column[Int]("ordering")
    def category   = column[String]("category")
    def job        = column[Option[String]]("job",O.Default(null))
    def characters = column[Option[String]]("characters",O.Default(null))

    def * : ProvenShape[TitlePrincipal] =
      (
        tconst,
        ordering,
        category,
        job,
        characters
      ) <> ((TitlePrincipal.apply _).tupled, TitlePrincipal.unapply)
  }

  protected val titlePrincipalsTable = TableQuery[TitlePrincipalTable]

}
