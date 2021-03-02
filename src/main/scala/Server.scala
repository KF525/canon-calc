import cats.effect.Resource
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
import org.http4s.client.Client
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.implicits._
import org.http4s.server.Router
import zio.clock.Clock

object Server {
  /**
   * Creates a Resource. This method is strictly about setting up the provisioning and releasing of a resource,
   * but does not eagerly evaluate that resource. We do not run the effect of getting the actual client until .use
   * is called when we start the Service.
   * @param runtime
   * @param descriptor
   * @return Resource
   *
   */
  def createClient(runtime: Runtime[zio.ZEnv], descriptor: Fiber.Descriptor): Resource[Task, Client[Task]] = {
    val executionContext = descriptor.executor.asEC
    implicit val rt = runtime
    BlazeClientBuilder[Task](executionContext).resource
  }

  def start(host: String, port: Int): Task[Unit] =
    for {
      descriptor <- ZIO.descriptor
      runtime: Runtime[zio.ZEnv] = Runtime.default
      httpApp <- createClient(runtime, descriptor).use {
        c => Task(Router("/" -> (Routes.canonRoute(c))).orNotFound) //access to resource I've provisioned
        // router needs access to client, wrapped it in task use needs return type of Task
      }
      server <- {
        val executionContext = descriptor.executor.asEC
        implicit val rt = runtime
        BlazeServerBuilder[Task](executionContext)
          .bindHttp(port, host)
          .withHttpApp(httpApp)
          .serve
          .compile
          .drain
      }
    } yield ()
}