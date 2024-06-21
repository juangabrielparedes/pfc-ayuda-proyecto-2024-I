package proyecto


class Itinerario() {

  type Aeropuertos = List[Aeropuerto]
  type Vuelos = List[Vuelo]

  def itinerarios(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[List[Vuelo]] = {
    (cod1: String, cod2: String) => {
      def buscarItinerarios(origen: String, destino: String, visitados: Set[String]): List[List[Vuelo]] = {
        if (origen == destino) List(List())
        else {
          vuelos.filter(v => v.Org == origen && !visitados.contains(v.Dst)).flatMap { vuelo =>
            val itinerariosRestantes = buscarItinerarios(vuelo.Dst, destino, visitados + vuelo.Dst)
            itinerariosRestantes.map(it => vuelo :: it)
          }
        }
      }

      buscarItinerarios(cod1, cod2, Set())
    }
  }

  def itinerariosTiempo(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[List[Vuelo]] = {
    (cod1: String, cod2: String) => {
      val todosItinerarios = itinerarios(vuelos, aeropuertos)(cod1, cod2)
      todosItinerarios.sortBy(calcularTiempoEnAire(vuelos)).take(3)
    }
  }

  def itinerariosEscalas(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[List[Vuelo]] = {
    (cod1: String, cod2: String) => {
      val todosItinerarios = itinerarios(vuelos, aeropuertos)(cod1, cod2)
      todosItinerarios.sortBy(_.length).take(3)
    }
  }

  def itinerariosAire(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String) => List[List[Vuelo]] = {
    (cod1: String, cod2: String) => {
      val todosItinerarios = itinerarios(vuelos, aeropuertos)(cod1, cod2)
      todosItinerarios.sortBy(calcularTiempoEnAire(vuelos)).take(3)
    }
  }

  def calcularTiempoEnAire(vuelos: List[Vuelo])(itinerario: List[Vuelo]): Int = {
    itinerario.map { vuelo =>
      val horaSalidaEnMinutos = vuelo.HS * 60 + vuelo.MS
      val horaLlegadaEnMinutos = vuelo.HL * 60 + vuelo.ML
      horaLlegadaEnMinutos - horaSalidaEnMinutos
    }.sum
  }

  def itinerariosSalida(vuelos: List[Vuelo], aeropuertos: List[Aeropuerto]): (String, String, Int, Int) => List[List[Vuelo]] = {
    (cod1: String, cod2: String, HC: Int, MC: Int) => {
      val todosItinerarios = itinerarios(vuelos, aeropuertos)(cod1, cod2)
      todosItinerarios.filter(it => cumpleHoraSalida(it, HC, MC)).sortBy(it => it.head.HS * 60 + it.head.MS).take(3)
    }
  }


  def cumpleHoraSalida(itinerario: List[Vuelo], HC: Int, MC: Int): Boolean = {
    val horaCitaEnMinutos = HC * 60 + MC
    itinerario.headOption match {
      case Some(primerVuelo) => primerVuelo.HS * 60 + primerVuelo.MS <= horaCitaEnMinutos
      case None => false
    }
  }
}