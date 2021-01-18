import kotlin.native.concurrent.ThreadLocal

//Singleton objects are frozen by default
object  GlobalSingleton {
    var i = 0
    fun increment() {
        i++ // warning required as this is a mutating function
    }
    val data = SomeData("test")
}

//An attempt to mutate singleton will lead to exception
fun mutateSingleton() {
    GlobalSingleton.i = 1
}

//A call to mutating function will also throw an exception
fun callMutatingFunctionOnSingleton() {
    GlobalSingleton.increment()
}
//An attempt to mutate properties of properties will also throw an exception
// because entire object subgraph is frozen
fun mutateSingletonProps() {
    GlobalSingleton.data.name = "new"
}

class ClassWithCompanion {
    companion object CompanionObject {
        var i = 0
    }
}

fun mutateCompanionObject() {
    ClassWithCompanion.i = 1
}

//BUT @ThreadLocal annotated singleton objects are mutable
@ThreadLocal
object  ThreadLocalSingleton {
    var i = 0
    fun increment() {
        i++
    }
}

//Below code does not does not throw exceptions
fun mutateThreadLocalSingleton() {
    ThreadLocalSingleton.i = 1
    ThreadLocalSingleton.increment()
}