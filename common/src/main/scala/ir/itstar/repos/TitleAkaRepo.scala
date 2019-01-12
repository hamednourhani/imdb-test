package ir.itstar.repos
import ir.itstar.db.DatabaseConnector
import ir.itstar.models.TitleAka
import slick.lifted.ProvenShape

trait TitleAkaRepo {}

object TitleAkaRepoImpl extends TitleAkaRepo with TitleAkaComponent {}

trait TitleAkaComponent {

  import DatabaseConnector.profile.api._

  private[TitleAkaComponent] final class TitleAkaTable(tag: Tag) extends Table[TitleAka](tag, "title_basics") {

    def titleId         = column[String]("titleId", O.PrimaryKey)
    def ordering        = column[Int]("ordering")
    def title           = column[String]("title")
    def region          = column[String]("region")
    def language        = column[String]("language")
    def types           = column[String]("types")
    def attributes      = column[Option[String]]("attributes", O.Default(null))
    def isOriginalTitle = column[Boolean]("isOriginalTitle")

    def * : ProvenShape[TitleAka] =
      (
        titleId,
        ordering,
        title,
        region,
        language,
        types,
        attributes,
        isOriginalTitle
      ) <> ((TitleAka.apply _).tupled, TitleAka.unapply)
  }

  protected val titleAkasTable = TableQuery[TitleAkaTable]

}
