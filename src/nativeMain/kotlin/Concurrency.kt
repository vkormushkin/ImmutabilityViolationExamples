import kotlinx.coroutines.*
import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.Worker

fun mutateWithinCoroutine () {

    val someData = SomeData("test")

    runBlocking {
        launch(Dispatchers.Unconfined) {
            someData.name = "new1" //No exception
        }
        println(someData.name)
        launch(Dispatchers.Default) {
            someData.name = "new2" //Exception because coroutine freezes input block on thread switch:
           //https://github.com/Kotlin/kotlinx.coroutines/blob/9eb3543aa6811d7bbfd7bbc9f7a2389f61e7c716/kotlinx-coroutines-core/native/src/Builders.kt#L130
        }
    }
}

fun giveSomeData() : SomeData{
    return SomeData("2")
}

fun mutateAsyncOutput () {
    var someData: SomeData? = null

    runBlocking {
        someData = withContext(Dispatchers.Default) {
            giveSomeData()
        }
    }

    someData?.name = "new" //Exception
}

fun mutateWithWorker() {
    val someData = SomeData("test")
    val worker = Worker.start()
    worker.execute(TransferMode.SAFE, {someData}) {
        it.name = "new" //This causes IllegalStateException unless freeze() is called on a producer
    }
}

