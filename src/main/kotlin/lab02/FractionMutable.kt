package lab02

import kotlin.math.abs

class FractionMutable(numerator: Int, denominator: Int, private var sign: Int = 1) {
    init {
        require(denominator != 0) { "Denominator cannot be zero" }
        require(sign == 1 || sign == -1) { "Sign must be 1 or -1" }
    }

    // Normalize the fraction during initialization
    private var num: Int = abs(numerator / gcd(numerator, denominator))
    private var denom: Int = abs(denominator / gcd(numerator, denominator))

    // Override toString to return the fraction in the format "numerator/denominator"
    override fun toString() = "${if (sign == -1) "-" else ""}$num/$denom"

    // Compute the greatest common divisor (GCD) using Euclidean algorithm
    private fun gcd(a: Int, b: Int): Int {
        var num1 = a
        var num2 = b
        while (num2 != 0) {
            val temp = num2
            num2 = num1 % num2
            num1 = temp
        }
        return num1
    }

    // Negate the sign of the fraction
    fun negate() {
        this.sign = -this.sign
    }

    // Add another fraction to this fraction
    fun add(other: FractionMutable) {
        val numerator = this.num * other.denom * this.sign + other.num * this.denom * other.sign
        val denominator = this.denom * other.denom
        val g = gcd(numerator, denominator)
        this.num = abs(numerator / g)
        this.denom = abs(denominator / g)
        this.sign = if (numerator < 0) -1 else 1
    }

    // Multiply this fraction by another fraction
    fun mult(other: FractionMutable) {
        val numerator = this.num * other.num
        val denominator = this.denom * other.denom
        val g = gcd(numerator, denominator)
        this.num = abs(numerator / g)
        this.denom = abs(denominator / g)
        this.sign = this.sign * other.sign
    }

    // Divide this fraction by another fraction
    fun div(other: FractionMutable) {
        if (other.num != 0) {
            this.mult(FractionMutable(other.denom, other.num, this.sign))
        } else {
            throw ArithmeticException("Cannot divide by zero")
        }
    }

    // Get the integer part of the fraction
    fun intPart() = num / denom
}

