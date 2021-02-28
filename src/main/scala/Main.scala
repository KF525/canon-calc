import cats.FlatMap
import cats.effect.{Timer, _}
import fs2.Stream
import org.http4s.{Http, HttpApp, HttpRoutes, Request, Response}
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.dsl.Http4sDsl
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.implicits._
import cats.implicits._
import cats.instances._
import org.http4s.server.Router

import scala.concurrent.ExecutionContext.global

object Main extends IOApp {

    def run(args: List[String]) = {
        Server.start[IO].compile.drain.as(ExitCode.Success)
    }
}
