import cats.effect.{ConcurrentEffect, Timer}
import org.http4s._
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.dsl.Http4sDsl
import org.http4s.server.blaze.BlazeServerBuilder
import fs2.Stream
import org.http4s.server.Router
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

object Server {
  def start[F[_] : ConcurrentEffect : Timer]: Stream[F, ExitCode] = {
    val routes = (Routes.canonRoute).orNotFound
    val host = "localhost"
    val port = 8080
    for {
      client <- BlazeClientBuilder[F](global).stream
      exitCode <- BlazeServerBuilder[F]
        .bindHttp(port, host)
        .withHttpApp(routes)
        .serve
    } yield exitCode
  }
}