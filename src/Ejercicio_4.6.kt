data class Compra(val cliente: Cliente, val diaCompra: Int, var monto: Double)

data class Cliente(val nombre: String, val domicilio: Domicilio)

class Domicilio(private val calle: String, private val numero: Int) {
    fun dirCompleta(): String {
        return "$calle, nº$numero"
    }
}

class RepositorioCompra {
    val compras: MutableList<Compra> = mutableListOf()

    fun agregar(compra: Compra) {
        compras.add(compra)
    }

    fun agregar(cliente: Cliente, dia: Int, monto: Double) {
        val compra = Compra(cliente, dia, monto)
        compras.add(compra)
    }

    fun domicilioS(): List<Domicilio> {
        val domicilios = mutableSetOf<Domicilio>()

        for (compra in compras) {
            domicilios.add(compra.cliente.domicilio)
        }
        return domicilios.toList()  // Convertimos el Set a una List para devolverlo
    }
}

fun main() {

    val domicilio1 = Domicilio("Calle Soledad", 1)
    val domicilio2 = Domicilio("Avenida Huelva", 2)


    val cliente1 = Cliente("Luque", domicilio1)
    val cliente2 = Cliente("Fran", domicilio2)


    val repositorio = RepositorioCompra()


    val compra1 = Compra(cliente1, 1, 50.0)
    val compra2 = Compra(cliente2, 2, 30.0)
    val compra3 = Compra(cliente1, 3, 1000.0)


    repositorio.agregar(compra1)
    repositorio.agregar(compra2)
    repositorio.agregar(compra3)


    repositorio.agregar(cliente2, 4, 250.0)

    println("Compras registradas: ")
    for (compra in repositorio.compras) {
        println("Cliente: ${compra.cliente.nombre}, Día: ${compra.diaCompra}, Monto: ${compra.monto}")
    }

    println("\nDomicilios registrados: ")
    val domicilios = repositorio.domicilioS()
    for (domicilio in domicilios) {
        println(domicilio.dirCompleta())
    }
}
