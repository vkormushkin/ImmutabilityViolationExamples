import kotlin.native.concurrent.InvalidMutabilityException

data class SomeData(var name: String)

fun main() {
    try {
        Platform.isMemoryLeakCheckerActive = false //prevent worker leaking error
    } catch (e: InvalidMutabilityException) {
        println("************")
        println("InvalidMutabilityException")
        println("************")
        return
    } catch (e : Exception) {
        println("Some other exception")
        println(e)
        return
    }

    println("No Exception")}