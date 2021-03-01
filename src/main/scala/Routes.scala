import zio._
import org.http4s._
import org.http4s.dsl.Http4sDsl
import zio.interop.catz._

import scala.io.Source

object Routes {

    val dsl = Http4sDsl[Task]
    import dsl._

    val canonRoute = HttpRoutes.of[Task]{
      case GET -> Root / "text" / textId  => 
        val uri: Either[ParseFailure, Uri] = Uri.fromString("https://www.gutenberg.org/files/2701/2701-0.txt")
//        val baseUri = Uri.uri("https://www.gutenberg.org")
//        val withPath = baseUri.withPath("/files")
//        val withQuery = withPath.withQueryParam("hello", "world")
        val text = Source.fromURL("https://www.gutenberg.org/files/2701/2701-0.txt")
        //val helloJames = httpClient.expect[String]("http://localhost:8080/hello/James")
        Ok(text.mkString)
    }
}
