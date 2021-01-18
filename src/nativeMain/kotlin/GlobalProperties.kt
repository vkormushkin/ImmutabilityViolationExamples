import kotlin.native.concurrent.SharedImmutable

//Shared immutable global properties are frozen
@SharedImmutable
val immutableVal = SomeData("test")

fun mutateSharedImmutable() {
    immutableVal.name = "new" //exception
}

//Normal global props are mutable
val mutableVal = SomeData("test")

fun case4() {
    mutableVal.name = "new" //no exception
}