//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
package fundamentos
fun main(args: Array<String>) {
   println(soma(2,3))
    println(soma(11))
}

fun soma(a: Int, b:Int = 1):Int{
    return a + b
}