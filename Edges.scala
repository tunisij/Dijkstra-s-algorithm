package dollDeliveryPrj

  /******************************************************************
   * @author John Tunisi
   * @version 7/24/14
   * 
   * Holds data on an edge, or path connecting two vertices.
   *****************************************************************/

	class Edges(sideA: Vertex, sideB: Vertex, edgeWeight: Int){
	  var weight = edgeWeight
	  var side1 = sideA
	  var side2 = sideB
	  
	  def getWeight(): Int = weight
	  def setWeight(edgeWt: Int) = {weight = edgeWt}
	  
	  def getSideA(): Vertex = side1
	  def setSideA(edgeSideA: Vertex) = {side1 = edgeSideA}
	  
	  def getSideB(): Vertex = side2
	  def setSideB(edgeSideB: Vertex) = {side2 = edgeSideB}
	  
	}