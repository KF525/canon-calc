import zio._
import zio.console._
import zio.interop.catz._
import cats.implicits._
import org.http4s._
import org.http4s.implicits._
import org.http4s.server.Router

import scala.concurrent.ExecutionContext.global

object Main extends zio.App {
    def run(args: List[String]) = {
        appLogic.exitCode
    }
    
    val appLogic =
        for {
            _ <- putStrLn("Building Server")
            _ <- Server.start("localhost", 8080)
        } yield ()
}
