import zio.console._
import zio._
import org.http4s._
import org.http4s.dsl.Http4sDsl
import zio.interop.catz._
import zio.interop.catz.implicits._
import org.http4s.server.blaze.BlazeServerBuilder
import zio._
import zio.console._
import zio.interop.catz._
import cats.implicits._
import org.http4s._
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.implicits._
import org.http4s.server.Router

object Server {
  def start(host: String, port: Int): Task[Unit] =
    for {
      descriptor <- ZIO.descriptor
      runtime = Runtime.default
      httpApp = Router("/" -> (Routes.canonRoute)).orNotFound
      server <- {
        implicit val rt = runtime
        val executionContext = descriptor.executor.asEC
        BlazeServerBuilder[Task](executionContext)
          .bindHttp(port, host)
          .withHttpApp(httpApp)
          .serve
          .compile
          .drain
      }
    } yield ()
}