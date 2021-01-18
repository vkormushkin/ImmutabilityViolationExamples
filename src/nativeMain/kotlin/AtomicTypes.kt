import kotlin.native.concurrent.AtomicInt

//Atomic types allow to workaround immutability of frozen objects
//AtomicInt, AtomicLong, AtomicReference.
object AtomicSingleton {
    var i : AtomicInt = AtomicInt(0)
}

fun reassignAtomicType() {
    AtomicSingleton.i = AtomicInt(1) //exception
}

fun mutateAtomicType() {
    AtomicSingleton.i.value = 2 //no exception
    AtomicSingleton.i.increment() //no exception
}
