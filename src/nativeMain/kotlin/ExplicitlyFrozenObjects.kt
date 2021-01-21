import kotlin.native.concurrent.freeze

//Objects can be frozen explicitly and must not be mutated post that
//Note that once object is frozen its entire object subgraph is frozen as well
fun mutateFrozen() {
    val data = SomeData("Test")
    data.freeze()
    data.name = "new1" //exception
    var i = 0
    i.freeze()
    i++ //exception
    println(i)
}

fun mutateThenFreeze() {
    val data = SomeData("Test")
    data.name = "new2" //no exception
    data.freeze()
}
