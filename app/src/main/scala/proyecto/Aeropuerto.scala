package proyecto

case class Aeropuerto(Cod: String, X: Int, Y: Int, GMT: Double)
def distancia(a: Aeropuerto, b: Aeropuerto): Double = {
  val R = 6371
  val x1 = a.X
  val y1 = a.Y
  val x2 = b.X
  val y2 = b.Y
  val d = R * Math.acos(Math.sin(x1) * Math.sin(x2) + Math.cos(x1) * Math.cos(x2) * Math.cos(y1 - y2))
  d
}
case class Vuelo(Aln: String, Num: Int, Org: String, HS: Int, MS:Int, Dst: String, HL: Int, ML: Int, Esc:Int)
