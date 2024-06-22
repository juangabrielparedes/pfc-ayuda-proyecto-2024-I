/*package proyecto

import proyecto.Aeropuerto
import proyecto.Vuelo
case class Aeropuerto(Cod: String, X: Int, Y: Int, GMT: Double)
case class Vuelo(Aln: String, Num: Int, Org: String, HS: Int, MS: Int, Dst: String, HL: Int, ML: Int, Esc: Int)
case class Itinerario(itinerario: List[Vuelo]) {
  def calcularTiempoEnAire: Int = {
    itinerario.map { vuelo =>
      val horaSalidaEnMinutos = vuelo.HS * 60 + vuelo.MS
      val horaLlegadaEnMinutos = vuelo.HL * 60 + vuelo.ML
      horaLlegadaEnMinutos - horaSalidaEnMinutos
    }.sum
  }
}

class ItinerariosPar() {
  type aeropuertos = List[Aeropuerto]
  type vuelos = List[Vuelo]

  def itinerariosPar(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {
    // Retorna una función que calcula todos los itinerarios posibles entre cod1 y cod2
    (cod1: String, cod2: String) => {
      def buscarItinerarios(origen: String, destino: String, visitados: Set[String]): List[Itinerario] = {
        if (origen == destino) List(Itinerario(List()))
        else {
          vuelos.filter(v => v.Org == origen & visitados.contains(v.Dst)).flatMap { vuelo =>
            val itinerariosRestantes = buscarItinerarios(vuelo.Dst, destino, visitados + vuelo.Dst)
            itinerariosRestantes.map(it => Itinerario(vuelo :: it.itinerario))
          }
        }
      }

      buscarItinerarios(cod1, cod2, Set())
    }
  }

  def itinerariosTiempo(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {
    // Retorna una función que calcula los tres itinerarios con menor tiempo entre cod1 y cod2
    (cod1: String, cod2: String) => {
      val todosItinerarios = itinerariosPar(vuelos, aeropuertos)(cod1, cod2)
      todosItinerarios.sortBy(_.calcularTiempoEnAire).take(3)
    }
  }

  def itinerariosEscalasPar(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario]
  = {
    // Retorna una función que calcula los tres itinerarios con menor número de escalas entre cod1 y cod2
    (cod1: String, cod2: String) => {
      val todosItinerarios = itinerariosPar(vuelos, aeropuertos)(cod1, cod2)
      todosItinerarios.sortBy(_.itinerario.length).take(3)
    }
  }

  def itinerariosAirePar(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[Itinerario] = {
    // Retorna una función que calcula los tres itinerarios con menor tiempo en aire entre cod1 y cod2
    (cod1: String, cod2: String) => {
      val todosItinerarios = itinerariosPar(vuelos, aeropuertos)(cod1, cod2)
      todosItinerarios.sortBy(_.calcularTiempoEnAire).take(3)
    }
  }

  def itinerariosSalidaPar(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String, Int, Int) => List[Itinerario] = {
    // Retorna una función que calcula los tres itinerarios que permiten llegar a una hora de cita HC:MC entre cod1 y cod2
    (cod1: String, cod2: String, HC: Int, MC: Int) => {
      val todosItinerarios = itinerariosPar(vuelos, aeropuertos)(cod1, cod2)
      todosItinerarios.filter(it => cumpleHoraSalida(it.itinerario, HC, MC))
        .sortBy(it => it.itinerario.headOption.map(v => v.HS * 60 + v.MS).getOrElse(Int.MaxValue)).take(3)
    }
  }

  private def cumpleHoraSalida(itinerario: List[Vuelo], HC: Int, MC: Int): Boolean = {
    val horaCitaEnMinutos = HC * 60 + MC
    itinerario.headOption.exists(primerVuelo => primerVuelo.HS * 60 + primerVuelo.MS <= horaCitaEnMinutos)
  }
}*/