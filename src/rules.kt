import java.io.File
import kotlin.math.log

abstract class Rule(pswd_ : String) {
    abstract fun checkValid() : Boolean
    open var pswd = pswd_
}

class ruleLength(pswd: String): Rule(pswd) {
    var length = 10
    override fun checkValid() : Boolean {
        if (this.pswd.length >= this.length) return true
        return false
    }
}

class ruleRegisters(pswd: String): Rule(pswd) {
    override fun checkValid() : Boolean {
        val u = Regex("[A-Z]")
        val d = Regex("[a-z]")
        if (u.containsMatchIn(pswd) && d.containsMatchIn(pswd)) return true
        return false
    }
}

class ruleDigitsSymbols(pswd: String): Rule(pswd) {
    override fun checkValid() : Boolean {
        val r = Regex("[0-9!! \"#\\\$%&'\\(\\)\\*\\+,-\\.\\/:;<=>\\?@\\[\\\\\\]\\^_`{\\|}~]")
        if (r.containsMatchIn(pswd)) return true
        return false
    }
}

class ruleDict(pswd: String): Rule(pswd) {
    var path = "pswd-dict.txt"
    override fun checkValid() : Boolean {
        var is_valid = true
        File(path).forEachLine {
            if (it == pswd) is_valid = false
        }
        return is_valid
    }
}
class ruleEntropy(pswd: String): Rule(pswd) {
    var entropy_limit = 3;
    override fun checkValid() : Boolean {
        var entropy : Double = 0.0
        var count = 0
        val n = pswd.length
        for (c in pswd) {
            count = n - pswd.replace(c.toString(), "").length
            entropy += (count / n.toDouble()) * log(count / n.toDouble(), 2.0)
        }
        entropy = -entropy
        if (entropy > this.entropy_limit) return true
        return false
    }
}