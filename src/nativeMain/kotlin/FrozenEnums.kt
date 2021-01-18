//Enums are frozen by default
enum class SomeEnum {

    Test;

    val data = SomeData("test")
}

fun mutateEnum() {
    val enumVal = SomeEnum.Test
    enumVal.data.name = "new" //exception
}
