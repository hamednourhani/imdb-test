package ir.itstar.repos

import ir.itstar.db.DatabaseConnector
import ir.itstar.models.TitleBasic
import slick.lifted.ProvenShape

trait TitleBasicRepo {}

object TitleBasicRepoImpl extends TitleBasicRepo with TitleBasicComponent {}

trait TitleBasicComponent {

  import DatabaseConnector.profile.api._

  private[TitleBasicComponent] final class TitleBasicTable(tag: Tag) extends Table[TitleBasic](tag, "title_basics") {

    def tconst         = column[String]("tconst")
    def titleType      = column[String]("titleType")
    def primaryTitle   = column[String]("primaryTitle")
    def originalTitle  = column[String]("originalTitle")
    def isAdult        = column[Boolean]("isAdult")
    def startYear      = column[Int]("startYear")
    def endYear        = column[Int]("endYear")
    def runtimeMinutes = column[Long]("runtimeMinutes")
    def genres         = column[String]("genres")

    def * : ProvenShape[TitleBasic] =
      (
        tconst,
        titleType,
        primaryTitle,
        originalTitle,
        isAdult,
        startYear,
        endYear,
        runtimeMinutes,
        genres
      ) <> ((TitleBasic.apply _).tupled, TitleBasic.unapply)
  }

  protected val titleBasicsTable = TableQuery[TitleBasicTable]

}
