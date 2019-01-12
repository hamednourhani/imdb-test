package ir.itstar.repos

import ir.itstar.db.DatabaseConnector
import ir.itstar.models.TitleCrew
import slick.lifted.ProvenShape

trait TitleCrewRepo {}

object TitleCrewRepoImpl extends TitleCrewRepo with TitleCrewComponent {}

trait TitleCrewComponent {

  import DatabaseConnector.profile.api._

  private[TitleCrewComponent] final class TitleCrewTable(tag: Tag) extends Table[TitleCrew](tag, "title_crews") {

    def tconst    = column[String]("tconst")
    def directors = column[String]("directors")
    def writers   = column[String]("writers")

    def * : ProvenShape[TitleCrew] =
      (
        tconst,
        directors,
        writers,
      ) <> ((TitleCrew.apply _).tupled, TitleCrew.unapply)
  }

  protected val titleCrewsTable = TableQuery[TitleCrewTable]

}
