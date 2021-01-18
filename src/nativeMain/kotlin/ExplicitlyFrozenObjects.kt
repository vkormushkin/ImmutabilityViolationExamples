import kotlin.native.concurrent.freeze

//Objects can be frozen explicitly and must not be mutated post that
//Note that once object is frozen its entire object subgraph is frozen as well
fun mutateFrozen() {
    val data = SomeData("Test")
    data.freeze()
    data.name = "new" //exception
}

fun freezeThenMutate() {
    val data = SomeData("Test")
    data.name = "new" //no exception
    data.freeze()
}
