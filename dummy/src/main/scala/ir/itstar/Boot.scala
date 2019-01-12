package ir.itstar
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.typesafe.scalalogging.LazyLogging
import ir.itstar.db.{DBInitializer, DBPopulate, DatabaseConnector}

import scala.concurrent.{ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}

object Boot extends App with LazyLogging{

  implicit val system: ActorSystem = ActorSystem("dummy")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val ec: ExecutionContextExecutor = system.dispatcher

  //Note: create dbs before population if not exists
//  val init: Future[List[Unit]] = DBInitializer.initDb()
//
//  init
//    .andThen{case _ => system.terminate()}
//    .onComplete{
//    case Success(_) =>
//      logger.info("init db succeeded")
//    case Failure(e) =>
//    logger.error("error while initiating db",e)
//  }
//
//  sys.addShutdownHook(system.terminate())
//  sys.addShutdownHook(DatabaseConnector.closeDB())

  DBPopulate.populate()
}
