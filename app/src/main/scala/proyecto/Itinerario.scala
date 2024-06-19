package proyecto

class Itinerario() {

  type aeropuertos = List[Aeropuerto]
  type vuelos = List[Vuelo]

  def itinerarios(vuelos: List[Vuelo], aeropuertos:List[Aeropuerto]): (String, String) => List[Itinerario] = {
    //Recibe una lista de vuelos y aeropuertos
    //Retorna una función que recibe los codigos de dos aeropuertos
    //Retorna todos los itinerarios posibles de cod1 a cod2
    (c1:String, c2:String) => {
      // Calcular los itinerarios de un sólo vuelo directo entre c1 y c2
      val itDirectos = vuelos.filter((p: Vuelo) => (p.Org.equals(c1) && p.Dst.equals(c2))).map(x => List(x))
      // Calcula los itinerarios cuyo primer vuelo no llega a c2 y el resto es un
      // itinerario de donde llega el primer vuelo hasta c2 sin pasar por c1
      val itIndirectos = for {
        v1 <- vuelos.filter((p: Vuelo) => (p.Org.equals(c1) && !p.Dst.equals(c2)))
        it <- itinerarios(vuelos.filter(v=>(v.Dst != c1)),aeropuertos)(v1.Dst, c2)
      } yield v1::it
      itDirectos ++ itIndirectos
    }
  }

  def itinerariosTiempo(vuelos: List[Vuelo], aeropuertos:List[Aeropuerto]): (String, String) => List[Itinerario] = {
    //Recibe vuelos, una lista de vuelos y aeropuertos, una lista de aeropuertos y retorna una funcion que recibe dos strings y retorna una lista de itinerarios
    //Devuelve una función que recibe c1 y c2, códigos de aeropuertos
    //y devuelve una función que devuelve los tres (si los hay) itinerarios que minimizan el tiempo total de viaje
    (cod1:String, cod2:String)=> List[Itinerario]()
  }

  def itinerariosEscalas(vuelos:List[Vuelo], aeropuertos:List[Aeropuerto]):(String, String)=>List[Itinerario]
  = {
    //Recibe una lista de vuelos y aeropuertos
    //Retorna una función que recibe los codigos de dos aeropuertos
    //Retorna todos los tres mejores itinerarios posibles de cod1 a cod2
    //que minimizan el número de escalas
    (cod1:String, cod2:String)=> List[Itinerario]()
  }

  def itinerariosAire(vuelos: List[Vuelo], aeropuertos:List[Aeropuerto]): (String, String) => List[Itinerario] = {
    //Recibe una lista de vuelos y aeropuertos
    //Retorna una función que recibe los codigos de dos aeropuertos
    //Retorna todos los tres mejores itinerarios posibles de cod1 a cod2
    //que minimizan el tiempo en itinerarios
    (cod1:String, cod2:String)=> List[Itinerario]()
  }

  def itinerariosSalida(vuelos: List[Vuelo], aeropuertos:List[Aeropuerto]): (String, String, Int, Int) => List[Itinerario] = {
    //Recibe una lista de vuelos y aeropuertos
    //Retorna una función que recibe los codigos de dos aeropuertos y dos enteros, que es la hora de la cita
    //Retorna todos los tres mejores itinerarios posibles de cod1 a cod2
    //que permiten llegar a una hora de la cita
    (cod1:String, cod2:String, HC:Int, MC:Int)=> List[Itinerario]()
  }

}
