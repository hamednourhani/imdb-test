package ir.itstar.models

/**
  * tconst (string) - alphanumeric unique identifier of the title
  * ordering (integer) – a number to uniquely identify rows for a given titleId
  * nconst (string) - alphanumeric unique identifier of the name/person
  * category (string) - the category of job that person was in
  * job (string) - the specific job title if applicable, else '\N'
  * characters (string) - the name of the character played if applicable, else '\N'
  */
case class TitlePrincipal(
    tconst: String,
    ordering: Int,
    category: String,
    job: String,
    characters: String,
)
