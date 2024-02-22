package fundamentos.controles
fun main(args: Array<String>){
    for( i in 0 .. 100 step 5){ //INTERVALO FIXO COM PASSO
        println(i)
    }
    for( i in 100  downTo  0 step 5){
        println("i  = $i")
    }

}   
 
 