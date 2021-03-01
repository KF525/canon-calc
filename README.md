## sbt project compiled with Scala 3

### Usage

This is a normal sbt project. You can compile code with `sbt compile`, run it with `sbt run`, and `sbt console` will start a Scala 3 REPL.

For more information on the sbt-dotty plugin, see the
[dotty-example-project](https://github.com/lampepfl/dotty-example-project/blob/master/README.md).


FS2:

ZIO:

Http4s:
HTTP Application is just a function from a Request to a Response:

HttpApp = Request => IO[Response]
HttpRoutes = Request => IO[Option[Response]]