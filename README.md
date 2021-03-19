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
 
 Note: this currently leads to a malformed terraform file that does not aborts deployment
