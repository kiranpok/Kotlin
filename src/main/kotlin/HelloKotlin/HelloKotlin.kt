package HelloKotlin

fun shouldChangeWater(day:String, temperature:Int=22, dirty:Int=20):Boolean{
    return when{
        temperature>30 -> true
        dirty>30 -> true
        day=="Sunday" -> true
        else -> false

    }
}
fun isTooHot(temperature: Int)=temperature>30
fun isDirty(dirty: Int)=dirty>30
fun isSunday(day: String)=day=="Sunday"
fun main(){
    println(shouldChangeWater("Monday", 20, 50))
    println(shouldChangeWater("Sunday", 20, 50))
    println(shouldChangeWater("Sunday"))
    println(shouldChangeWater("Tuesday", 20, 20))

}
//Lambda
