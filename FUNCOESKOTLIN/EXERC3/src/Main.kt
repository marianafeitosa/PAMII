package funcoes

fun <E> filtrar(list: ArrayList<E>, filtro: (E) -> Boolean): List<E> {
    val listaFiltrada = ArrayList<E>()
    for (e in list) {
        if (filtro(e)){
            listaFiltrada.add(e)
        }
    }
    return listaFiltrada
}

fun comTresLetras(nome: String): Boolean{
    return nome.length == 3
}

fun main(args: Array<String>){
    val nomes = arrayListOf("Ana", "Pedro", "Gui", "Rebeca")
    println(filtrar(nomes, ::comTresLetras))
}
