import io.kotless.dsl.lang.http.Get

@Get("/hello")
fun sayHello() = "Say Hello!"
