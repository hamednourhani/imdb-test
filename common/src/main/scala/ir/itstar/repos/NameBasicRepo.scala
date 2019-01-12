package ir.itstar.repos

import ir.itstar.db.DatabaseConnector
import ir.itstar.models.NameBasic
import slick.lifted.ProvenShape

trait NameBasicRepo {}

object NameBasicRepoImpl extends NameBasicRepo with NameBasicComponent {}

trait NameBasicComponent {

  import DatabaseConnector.profile.api._

  private[NameBasicComponent] final class NameBasicTable(tag: Tag) extends Table[NameBasic](tag, "name_basics") {

    def nconst            = column[String]("nconst")
    def primaryName       = column[String]("primaryName")
    def birthYear         = column[String]("birthYear")
    def deathYear         = column[String]("deathYear")
    def primaryProfession = column[String]("primaryProfession")
    def knownForTitles    = column[String]("knownForTitles")

    def * : ProvenShape[NameBasic] =
      (
        nconst,
        primaryName,
        birthYear,
        deathYear,
        primaryProfession,
        knownForTitles
      ) <> ((NameBasic.apply _).tupled, NameBasic.unapply)
  }

  protected val nameBasicsTable = TableQuery[NameBasicTable]

}
