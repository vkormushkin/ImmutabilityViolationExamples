import kotlin.native.concurrent.InvalidMutabilityException

data class SomeData(var name: String)

fun main() {
    try {
    } catch (e: InvalidMutabilityException) {
        println("************")
        println("InvalidMutabilityException")
        println("************")
        return
    } catch (e : Exception) {
        println("Some other exception")
    }

    println("No Exception")}