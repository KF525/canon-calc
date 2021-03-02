import zio._
import org.http4s._
import org.http4s.client.Client
import org.http4s.dsl.Http4sDsl
import zio.interop.catz._

import scala.io.Source

object Routes {

    val dsl = Http4sDsl[Task]
    import dsl._

    def canonRoute(client: Client[Task]) = HttpRoutes.of[Task]{
      case GET -> Root / "text" / textId  => 
        //val uri: Either[ParseFailure, Uri] = Uri.fromString("https://www.gutenberg.org/files/2701/2701-0.txt")
//        val baseUri = Uri.uri("https://www.gutenberg.org")
//        val withPath = baseUri.withPath("/files")
//        val withQuery = withPath.withQueryParam("hello", "world")
        val text = Source.fromURL("https://www.gutenberg.org/files/2701/2701-0.txt")
        //val text = client.expect[String](Uri.uri("https://www.gutenberg.org/files/2701/2701-0.txt"))
        //client.expect[Task]("https://www.gutenberg.org/files/2701/2701-0.txt")
        Ok(text.mkString)
    }
}
