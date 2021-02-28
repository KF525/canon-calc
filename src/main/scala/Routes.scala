import org.http4s._
import org.http4s.dsl.Http4sDsl
import zio._
import zio.interop.catz._
import cats.Functor
import cats.effect._
import cats.syntax._
import cats.instances._
import cats.implicits._
import org.http4s.{EntityDecoder, HttpRoutes}
import org.http4s.dsl.Http4sDsl
import org.http4s.circe._

object Routes {
  
  def canonRoute[F[_]: Sync] = {
    val dsl = Http4sDsl[F]
    import dsl._
    HttpRoutes.of[F]{case GET -> Root / "texts" / textId / "body" =>
        //https://www.gutenberg.org/files/2701/2701-0.txt
        Ok(s"ok I got to the route $textId")
    }
  }
}
