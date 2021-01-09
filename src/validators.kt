class validatorException(message: String): Exception(message)

class Validator1(pswd_: String) {
    var pswd = pswd_
    fun checkValid() : Boolean {
        var is_valid = true
        if (!ruleEntropy(pswd).checkValid()) {
            println("Низкий уровень энтропии!")
            is_valid = false
        }
        if (!ruleDict(pswd).checkValid()) {
            println("Словарное слово!")
            is_valid = false
        }
        if (!ruleDigitsSymbols(pswd).checkValid()) {
            println("Отсутствие спец. символов или цифр!")
            is_valid = false
        }
        if (!ruleRegisters(pswd).checkValid()) {
            println("Отсутствие символов верхнего и нижнего регистра!")
            is_valid = false
        }
        if (!ruleLength(pswd).checkValid()) {
            println("Короткий пароль!")
            is_valid = false
        }
        return is_valid
    }
}