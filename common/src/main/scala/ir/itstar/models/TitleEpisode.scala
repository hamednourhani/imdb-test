package ir.itstar.models

/**
  * tconst (string) - alphanumeric identifier of episode
  * parentTconst (string) - alphanumeric identifier of the parent TV Series
  * seasonNumber (integer) – season number the episode belongs to
  * episodeNumber (integer) – episode number of the tconst in the TV series
  */
case class TitleEpisode(
    tconst: String,
    parentTconst: String,
    seasonNumber: Int,
    episodeNumber: Int
)
