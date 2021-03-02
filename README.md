## sbt project compiled with Scala 3

### Usage

This is a normal sbt project. You can compile code with `sbt compile`, run it with `sbt run`, and `sbt console` will start a Scala 3 REPL.

For more information on the sbt-dotty plugin, see the
[dotty-example-project](https://github.com/lampepfl/dotty-example-project/blob/master/README.md).

Running the Application:

FS2:

ZIO:

Http4s:
HTTP is just a Kleisli function from a streaming request to a polymorphic effect of a streaming response.
`type Http[F[_], G[_]] = Kleisli[F, Request[G], Response[G]]`

HTTP App is just HTTP where the response effect is the stream effect.
`type HttpApp[F[_]] = Http[F, F]`

HTTP Routes are just HTTP where the response context is an Option monad transformer applied to the stream effect ---so as to handle invalid routes gracefully (None).
```type HttpRoutes[F[_]] = Http[Option[F, ?], F]
object HttpRoutes {
   def of[F[_]: Sync()](pf: PartialFunction[Request[F], F[Response[F]]]): 
      HttpRoutes[Kleisli(req => OptionT(Sync[F].suspend(pf.lift(req), sequence)))]
}
```

[Http4s Documentation](https://http4s.org/)

[http4s: pure, typeful, functional HTTP in Scala – Ross Baker](https://www.youtube.com/watch?v=urdtmx4h5LE&t=1827s)
