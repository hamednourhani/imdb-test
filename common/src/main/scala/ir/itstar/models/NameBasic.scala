package ir.itstar.models

/**
  * nconst (string) - alphanumeric unique identifier of the name/person
  * primaryName (string)– name by which the person is most often credited
  * birthYear – in YYYY format
  * deathYear – in YYYY format if applicable, else '\N'
  * primaryProfession (array of strings)– the top-3 professions of the person
  * knownForTitles (array of tconsts) – titles the person is known for
  */
case class NameBasic(
    nconst: String,
    primaryName: String,
    birthYear: String,
    deathYear: Option[String]=None,
    primaryProfession: String,
    knownForTitles: String
)
