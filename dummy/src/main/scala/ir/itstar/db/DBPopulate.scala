package ir.itstar.db
import java.nio.file.Paths

import akka.NotUsed
import akka.stream.ActorMaterializer
import com.typesafe.config.{Config, ConfigFactory}

import scala.concurrent.{ExecutionContext, Future}
import akka.stream.alpakka.csv.scaladsl.CsvParsing
import akka.stream.scaladsl.{FileIO, Flow, Framing, Sink, Source}
import akka.util.ByteString

object DummyFiles {

  val config: Config   = ConfigFactory.load()
  val dummy: Config    = config.getConfig("dummy")
  val dummyDir: String = dummy.getString("dir")
  val files: Config    = dummy.getConfig("files")

  val nameBasicsFile: String      = s"$dummyDir/${files.getString("name-basics")}"
  val titleAkasFile: String       = s"$dummyDir/${files.getString("title-akas")}"
  val titleBasicsFile: String     = s"$dummyDir/${files.getString("title-basics")}"
  val titleCrewsFile: String      = s"$dummyDir/${files.getString("title-crews")}"
  val titleEpisodesFile: String   = s"$dummyDir/${files.getString("title-episodes")}"
  val titlePrincipalsFile: String = s"$dummyDir/${files.getString("title-principals")}"
  val titleRatingsFile: String    = s"$dummyDir/${files.getString("title-ratings")}"

}

object DBPopulate {

  val flow: Flow[ByteString, List[ByteString], NotUsed]
  = CsvParsing.lineScanner(CsvParsing.Tab)


  def parser  =
    Flow[ByteString].map(_.utf8String)



  def populate()(
    implicit
    ec: ExecutionContext,
    mat : ActorMaterializer
  ) : Future[_] ={

    FileIO.fromPath(Paths.get(ClassLoader.getSystemResource(DummyFiles.nameBasicsFile).toURI))
      .via(Framing.delimiter(ByteString("\n"),512))
      .via(parser)
      .take(10)
      .runWith(Sink.foreach(println))
  }


}
