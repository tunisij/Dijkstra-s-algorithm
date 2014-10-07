package dollDeliveryPrj

import scala.collection.mutable.ArrayBuffer

  /******************************************************************
   * @author John Tunisi
   * @version 7/24/14
   * 
   * Holds data on a vertex
   *****************************************************************/

	class Vertex(vertexName: String){
	  var distance = Int.MaxValue
	  var name = vertexName
	  var previousVertex: Vertex = null
	  var isDistanceDone = false
	  
	  def getDistance(): Int = distance
	  def setDistance(dist: Int) = {distance = dist}
	  
	  def getName(): String = name
	  def setName(vertName: String) = {name = vertName}
	  
	  def getPreviousVertex(): Vertex = previousVertex
	  def setPreviousVertex(vertex: Vertex) = {previousVertex = vertex}
	  
	  def getIsDone(): Boolean = isDistanceDone
	  def setIsDone(done: Boolean) = {isDistanceDone = done}
	  
	  //equals a vertex based on name
	  def equalsName(vert1: Vertex): Boolean = {
	    if((vert1.getName).equals(name)){ true }
	    else{ false }
	  }
	  
	  //equals a string based on name
	  def equalsName(strName: String): Boolean = {
	    if((name).equals(strName)){ true }
	    else { false }
	  }
	  
	  //equals a vertex based on all properties
	  def equals(vert1: Vertex): Boolean = {
	    if((vert1.getName).equals(name) &&
	        vert1.getDistance == distance){	
	      if(vert1.getPreviousVertex != null || previousVertex != null){
	    	  vert1.getPreviousVertex.getName.equals(previousVertex.getName)
	      }

	      true
	    }
	    else{ 
	      false
	    }
	  }
	  
	  //updates a vertex to another
	  def update(vert: Vertex) = {
	    distance = vert.getDistance
	    name = vert.getName
	    previousVertex = vert.getPreviousVertex
	  }
	}