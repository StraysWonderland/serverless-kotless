# Kotless-break-even

Simple kotlin break-even function to test kotless with its own DSL and with ktor DSL for aws lambda deployment

## Local Development
To run localy, run the "Kotless.local" gradle task.
```bash
> Task :local
11:44:18.167 [main] INFO  io.kotless.dsl.ktor.Kotless - Autoreload is disabled because the development mode is off.
11:44:18.196 [main] INFO  ktor.application - Autoreload is disabled because the development mode is off.
11:44:18.251 [main] INFO  ktor.application - Responding at http://0.0.0.0:8080
```

## Deployment
To deploy the project, exchange bucket, profile and region information in ``` build.gradle.kts ``` with the correct information

```gradle
kotless {
    config {
        bucket = "my.kotless.bucket"
        prefix = "dev"
        dsl {
            type = io.kotless.DSLType.Ktor
        }

        terraform {
            profile = "my.kotless.user"
            region = "eu-west-1"
        }
    }
    webapp {
        lambda {
            kotless {
                packages = setOf("info.novatec")
            }
        }
    }
 }
 ```
 
## Problems
### Note to deployment task
this currently leads to a malformed terraform file that aborts deployment due to Invalid data resource names
```bash
Error: Invalid data resource name

  on breakeven.tf line 157, in data "aws_iam_policy_document" "0_assume":
 157: data "aws_iam_policy_document" "0_assume" {
 
A name must start with a letter or underscore and may contain only letters,
digits, underscores, and dashes.
```
This could currently not be fixed either through adding the "prefix" command into gradle or through editing the kotless library and adding a prefix generation in the name as well:

In ```io.kotless.gen.GenerationContext.kt```
```kotlin
inner class Names {
        fun tf(vararg name: String) = tf(name.toList())
        fun tf(part: String, parts: Iterable<String>) = tf(part.plusIterable(parts))
        fun tf(parts: Iterable<String>, part: String) = tf(parts.plus(part))
     >  fun tf(name: Iterable<String>) = (schema.config.prefix.plusIterable(name)).flatMap { Text.deall(it) }.joinToString(separator = "_") { it.toLowerCase() }

        fun aws(vararg name: String) = aws(name.toList())
        fun aws(part: String, parts: Iterable<String>) = aws(part.plusIterable(parts))
        fun aws(parts: Iterable<String>, part: String) = aws(parts.plus(part))
        fun aws(name: Iterable<String>): String {
            return (schema.config.prefix.plusIterable(name)).flatMap { Text.deall(it) }.joinToString(separator = "-") { it.toLowerCase() }
        }
}
```
    


