package ir.itstar.models

/**
  * tconst (string) - alphanumeric unique identifier of the title
  * averageRating – weighted average of all the individual user ratings
  * numVotes - number of votes the title has received
  */
case class TitleRating(
    tconst: String,
    averageRating: Double,
    numVotes: Int
)
