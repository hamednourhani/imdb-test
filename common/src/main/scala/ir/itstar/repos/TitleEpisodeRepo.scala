package ir.itstar.repos

import ir.itstar.db.DatabaseConnector
import ir.itstar.models.TitleEpisode
import slick.lifted.ProvenShape

trait TitleEpisodeRepo {}

object TitleEpisodeRepoImpl extends TitleEpisodeRepo with TitleEpisodeComponent {}

trait TitleEpisodeComponent {

  import DatabaseConnector.profile.api._

  private[TitleEpisodeComponent] final class TitleEpisodeTable(tag: Tag)
      extends Table[TitleEpisode](tag, "title_episodes") {

    def tconst        = column[String]("tconst")
    def parentTconst  = column[String]("parentTconst")
    def seasonNumber  = column[Int]("seasonNumber")
    def episodeNumber = column[Int]("episodeNumber")

    def * : ProvenShape[TitleEpisode] =
      (
        tconst,
        parentTconst,
        seasonNumber,
        episodeNumber
      ) <> ((TitleEpisode.apply _).tupled, TitleEpisode.unapply)
  }

  protected val titleEpisodesTable = TableQuery[TitleEpisodeTable]

}
