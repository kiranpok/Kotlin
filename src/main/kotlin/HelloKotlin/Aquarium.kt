package HelloKotlin

open class Aquarium(width: Int = 20, height: Int = 40, length: Int = 100) {
    var length: Int = length
    var width: Int = width
    open var height: Int = height

    // Primary constructor logic

    init {
        println("aquarium initializing")
    }

    init{
        println("Width: $width cm " +
                "Height: $height cm " +
                "Length: $length cm ")
    }

    open var volume: Int
        get() = width * height * length / 1000 // 1000 cm^3 = 1 liter
        set(value){
            height = (value * 1000) / (width * length)
        }

    open var shape= "rectangle"

    open var water: Double = 0.0
        get() = volume * 0.9


    fun printSize() {
        println(shape)
        println("Width: $width cm " +
                "Height: $height cm " +
                "Length: $length cm ")
        // 1 l = 1000 cm^3
        println("Volume: $volume l")
        println("Volume: $volume l Water: $water l (${water/volume*100.0}% full)")
    }

    fun sayBye() = println("Goodbye!")

    //// Secondary constructor to calculate tank size based on the number of fish
    constructor(numberOfFish: Int):this(){
        val tank = numberOfFish * 2000 * 1.1// 2000 cm^3 per fish + extra room
        height = (tank / (length * width)).toInt() // Calculate height to match the tank volume
    }


}

class TowerTank(override var height: Int, var diameter: Int): Aquarium(height=height, width=diameter, length=diameter){

}


