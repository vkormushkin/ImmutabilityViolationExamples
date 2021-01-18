import kotlinx.coroutines.*

fun mutateWithinCoroutine () {

    val someData = SomeData("test")

    runBlocking {
       GlobalScope.launch {
            someData.name = "new" //Exception in case of multithreaded coroutines
        }.join()
    }
}

suspend fun giveSomeData() : SomeData{
    return SomeData("2")
}

fun mutateAsyncOutput () {

    var someData = SomeData("1")

    runBlocking {
        someData = async {
            giveSomeData()
        }.await()
    }

    someData.name = "3" //I would expect an exception here but it does not happen

}
