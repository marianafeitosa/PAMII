package funcoes
fun main(args: Array<String>) { //NA FUNÇÃO MAIN TEM DUAS VARIAVEIS SENDO DECLARADAS "a" "b"
    print("O menor valor é: ${min(3, 4)}")
}
fun min(a: Int, b:Int):Int{ //NA FUNÇÃO MIN ESSAS DUAS VARIAVEIS ESTAO SENDO COMPARADAS PARA MOSTRAR O MENOR VALOR ENTRE ELAS
    return if (a<b)a else b
}