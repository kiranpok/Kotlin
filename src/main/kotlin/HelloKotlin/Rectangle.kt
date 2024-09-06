package HelloKotlin

import kotlin.math.sqrt

class Rectangle (horizontal: Double, vertical: Double){


    init{
        require(horizontal > 0){"Horizontal must be greater than 0"}
        require(vertical > 0){"Vertical must be greater than 0"}
    }
     var horizontal = horizontal
    private set
    var vertical = vertical
    private set
    var area: Double
        get() = horizontal * vertical
        set(value){
            val a = sqrt(value/area)
            vertical *= a
            horizontal *= a

        }

}





fun main(){
    val r = Rectangle(2.0,3.0)
    println("Area: ${r.area}")
    r.area = 12.0
    println("Width: ${r.horizontal} Height: ${r.vertical}")
}