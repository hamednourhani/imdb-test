package ir.itstar.repos

import ir.itstar.db.DatabaseConnectorImpl
import ir.itstar.models.TitleRating
import slick.lifted.ProvenShape

trait TitleRatingRepo {}

object TitleRatingRepoImpl extends TitleRatingRepo with TitleRatingComponent {}

trait TitleRatingComponent {

  import DatabaseConnectorImpl.profile.api._

  private[TitleRatingComponent] final class TitleRatingTable(tag: Tag)
      extends Table[TitleRating](tag, "title_ratings") {

    def tconst        = column[String]("tconst")
    def averageRating = column[Double]("averageRating")
    def numVotes      = column[Int]("numVotes")

    def * : ProvenShape[TitleRating] =
      (
        tconst,
        averageRating,
        numVotes,
      ) <> ((TitleRating.apply _).tupled, TitleRating.unapply)
  }

  protected val titleRatingsTable = TableQuery[TitleRatingTable]

}
