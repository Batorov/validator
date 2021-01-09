fun main() {
    val v1 = Validator1("aaaaaaaAaaaa")
    v1.checkValid()
    println()

    v1.pswd = "password"
    v1.checkValid()
    println()

    v1.pswd = "PassWord123!"
    v1.checkValid()
}