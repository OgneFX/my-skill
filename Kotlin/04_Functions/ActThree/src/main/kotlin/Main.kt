fun main() {
    val TrikcyWord: String = "F2p)v\"y233{0->c}ttelciFc"//исходный текст
    val firstPart: String = divideFirst(TrikcyWord) //получаем первую часть текста
    val secondPart: String = divideLast(TrikcyWord) //получаем вторую часть текста

    println(firstDecription(firstPart) + secondDecription(secondPart)) //Печать полного текста

   }

fun divideLast (a:String) :String {
   val b:Int
   val c:String

  b=a.length/2
    c= a.drop(b)

    return c



} //функция отделяет заднюю часть от вводимого слова

fun divideFirst (a:String) :String {
    val b:Int
    val c:String

    b=a.length/2
    c= a.dropLast(b)

    return c



}//функция отделяет переднюю часть от вводимого слова

fun firstDecription (text:String):String {
    val transTextFirst: String = shift(text) {char->char+1}
        .replace("4", "u", true)
        .replace("5", "s", true)
    val transTextSecond: String = shift(transTextFirst) {char->char-3}
        .replace("0", "o", true)


    return transTextSecond
}
 //функция расшифровки первой части слова

fun secondDecription (text:String):String {

    val transText: String = shift(text){char->char-4}
                        .reversed()
                        .replace("_"," ", true)

   return transText
}//функция расшифровки второй части слова


fun shift (text:String, transformChar: (Char)->Char):String {

    return text.map(transformChar).joinToString("")
}//функция сдвига символов на b количество
