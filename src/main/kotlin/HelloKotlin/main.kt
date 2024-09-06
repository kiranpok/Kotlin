package HelloKotlin

fun buildAquarium() {
    val myAquarium = Aquarium(25,25,40)//Use of secondary constructor
    myAquarium.volume = 70
    myAquarium.printSize()
    myAquarium.sayBye()




}
fun main() {
    buildAquarium()
}