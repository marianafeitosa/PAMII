import kotlin.math.pow
import kotlin.math.sqrt


fun areaRetangulo(base: Double , altura: Double ): Double{
    return base * altura
}

fun areaTriangulo(base: Double, altura: Double): Double {
    return (base * altura) / 2
}

fun areaTrianguloEquilatero(lado: Double): Double {
    return (sqrt(3.0) / 4) * lado.pow(2)
}

fun areaTrianguloIsosceles(base: Double, altura: Double): Double {
    return (base * altura) / 2
}

fun areaTrianguloEscaleno(ladoA: Double, ladoB: Double, ladoC: Double): Double {
    val s = (ladoA + ladoB + ladoC) / 2
    return sqrt(s * (s - ladoA) * (s - ladoB) * (s - ladoC))
}

fun areaCircunferencia(raio: Double): Double {
    return Math.PI * raio.pow(2)
}

fun areaLosango(diagonalMaior: Double, diagonalMenor: Double): Double {
    return (diagonalMaior * diagonalMenor) / 2
}

fun areaTrapezio(baseMaior: Double, baseMenor: Double, altura: Double): Double {
    return ((baseMaior + baseMenor) * altura) / 2
}


fun main() {
    println("Área do Retângulo: ${areaRetangulo(5.0, 10.0)}")
    println("Área do Triângulo: ${areaTriangulo(4.0, 5.0)}")
    println("Área do Triângulo Equilátero: ${areaTrianguloEquilatero(4.0)}")
    println("Área do Triângulo Isósceles: ${areaTrianguloIsosceles(4.0, 5.0)}")
    println("Área do Triângulo Escaleno: ${areaTrianguloEscaleno(3.0, 4.0, 5.0)}")
    println("Área da Circunferência: ${areaCircunferencia(3.0)}")
    println("Área do Losango: ${areaLosango(4.0, 5.0)}")
    println("Área do Trapézio: ${areaTrapezio(4.0, 6.0, 5.0)}")

}



