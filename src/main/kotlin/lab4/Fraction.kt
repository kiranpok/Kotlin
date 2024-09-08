package lab4

import kotlin.math.abs
import kotlin.math.sign

class Fraction(numer: Int, denom: Int, private var sign: Int = 1) : Comparable<Fraction> {
    private val top: Int
    private val bottom: Int

    init {
        require(denom != 0) { "Denominator can't be zero." }

        // Handle negative signs in the denominator and numerator
        var num = numer
        var den = denom
        if (den < 0) {
            num = -num
            den = -den
        }

        val gcdValue = gcd(abs(num), abs(den))
        this.top = abs(num) / gcdValue
        this.bottom = abs(den) / gcdValue
        this.sign *= num.sign

        // Edge case where the numerator is zero; make sure the fraction is positive
        if (this.top == 0) {
            this.sign = 1
        }
    }

    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }

    override fun compareTo(other: Fraction): Int {
        val thisFraction = this.top * other.bottom * this.sign
        val otherFraction = other.top * this.bottom * other.sign
        return thisFraction.compareTo(otherFraction)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Fraction) return false
        return top == other.top && bottom == other.bottom && sign == other.sign
    }

    override fun hashCode(): Int {
        var result = top
        result = 31 * result + bottom
        result = 31 * result + sign
        return result
    }

    operator fun unaryMinus(): Fraction = Fraction(top, bottom, -sign)
    operator fun plus(other: Fraction): Fraction = this.add(other)
    operator fun times(other: Fraction): Fraction = this.multiply(other)
    operator fun minus(other: Fraction): Fraction = this.add(-other)

    fun add(other: Fraction): Fraction {
        val commonDen = this.bottom * other.bottom
        val newTop = this.sign * this.top * other.bottom + other.sign * other.top * this.bottom
        return Fraction(newTop, commonDen)
    }

    fun multiply(other: Fraction): Fraction {
        return Fraction(this.top * other.top, this.bottom * other.bottom, this.sign * other.sign)
    }

    fun divide(other: Fraction): Fraction {
        return Fraction(this.top * other.bottom, this.bottom * other.top, this.sign * other.sign)
    }

    fun negate(): Fraction {
        return Fraction(this.top, this.bottom, -this.sign)
    }

    override fun toString(): String {
        val numerStr = if (sign < 0) -top else top
        return "$numerStr/$bottom"
    }
}

fun main() {
    val a = Fraction(1, 2, -1)
    println(a) // -1/2
    println(a.add(Fraction(1, 3))) // -1/6
    println(a.multiply(Fraction(5, 2, -1))) // 5/4
    println(a.divide(Fraction(2, 1))) // 1/4
    println(-Fraction(1, 6) + Fraction(1, 2)) // 1/3
    println(Fraction(2, 3) * Fraction(3, 2)) // 1/1
    println(Fraction(1, 2) > Fraction(2, 3)) // false
}
