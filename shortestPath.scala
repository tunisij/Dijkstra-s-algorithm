package dollDeliveryPrj

import scala.collection.mutable.ArrayBuffer

object shortestPath {

  /******************************************************************
   * @author John Tunisi
   * @version 7/24/14
   * 
   * Doll Delivery which produces a map of the shortest route to
   * a destination given a starting point.
   *****************************************************************/
  
	private var edgeList: ArrayBuffer[Edges] = ArrayBuffer.empty
  
	/****************************************************************
	 * Converts a list of vertex to String to compare easily
	 ***************************************************************/
	def listToString(list: ArrayBuffer[Vertex]): String = {
		var a = 0
		var str = ""
	  
		if(!list.isEmpty){
			for(a <- list){
			  str += a.getName + ", "
			}
		}
		str
	}
	
	/****************************************************************
	 * Converts a list of edges to String to compare easily
	 ***************************************************************/
	def edgeListToString(list: ArrayBuffer[Edges]): String = {
	  var a = 0
	  var str = ""
	  for(a <- list){
	    str += ("Side A: " + a.getSideA.getName + ", ")
	  }
	  str += "\n"
	  for(a <- list){
	    str += ("Side B: " + a.getSideB.getName + ", ")
	  }
	  str
	}
	
	/****************************************************************
	 * Removes a vertex from a list
	 ***************************************************************/
	def remove(vert: Vertex, list: ArrayBuffer[Vertex]) = {
		var size = list.length - 1
		var isRemoved = false
		
		for(v <- 0 to size){
		  if(!isRemoved){
			if(list(v).getName.equals(vert.getName)){
				list -= list(v)
				isRemoved = true
			}
		  }
		}	
	}
	
	/****************************************************************
	 * Updates a vertex to a list
	 ***************************************************************/
	def updateToList(vert: Vertex, list: ArrayBuffer[Vertex]) = {
		var size = list.length - 1
		var isUpdated = false
		
		for(v <- 0 to size){
		  if(!isUpdated){
			if(list(v).getName.equals(vert.getName)){
				list -= list(v)
				list += vert
				isUpdated = true
			}
		  }
		}	
	}
	
	/****************************************************************
	 * Updates a vertex from a list
	 ***************************************************************/
	def updateFromList(vert: Vertex, list: ArrayBuffer[Vertex]): Vertex = {
		var size = list.length - 1
		var isUpdated = false
		var newVert: Vertex = null
		
		for(v <- 0 to size){
		  if(!isUpdated){
			if(list(v).getName.equals(vert.getName)){
			    newVert = list(v)
				isUpdated = true
			}
		  }
		}
		newVert
	}
	
	/****************************************************************
	 * Gets the index of a vertex in a list
	 ***************************************************************/
	def getIndexOf(vert: Vertex, list: ArrayBuffer[Vertex]): Int = {
		var size = list.length - 1
		var isFound = false
		var index = -1
		
		for(v <- 0 to size){
		  if(!isFound){
			if(list(v).getName.equals(vert.getName)){
				index = v
				isFound = true
			}
		  }
		}	
		index
	}
	
	/****************************************************************
	 * Finds the lowest distance between a list of vertex
	 ***************************************************************/
	def lowestDistance(vertList: ArrayBuffer[Vertex]): Vertex = {
	  if(!vertList.isEmpty){
		var lowest = vertList.head
	    var temp = vertList.tail
	    
	    vertList.foreach((v) => {
	      if(v.getDistance < lowest.getDistance)
	    	  lowest = v
	    })
	    lowest
	    }else{
	    	null
	    }
	  }
	
	/****************************************************************
	 * Creates vertices and edges
	 ***************************************************************/
	
	def dataToGraph(edges: List[Map[String, Any]]): ArrayBuffer[Vertex] = {
	  var vertexList: ArrayBuffer[Vertex] = ArrayBuffer.empty
	  
	  for(a <- edges){
	    var vert1 = new Vertex(a.head._2.toString)
	    var tempMap = a.tail
	    var vert2 = new Vertex(tempMap.head._2.toString)
	    tempMap = tempMap.tail
	    var weight = tempMap.head._2.asInstanceOf[Int]
   	    var edge1 = new Edges(vert1, vert2, weight)
	    var edge2 = new Edges(vert2, vert1, weight)

	    edgeList += edge1
	    
	    //if vertex doesn't exist in list, add
	    if(!(listToString(vertexList) contains (vert1.getName))){
	      vertexList += vert1
	    }
	    if(!(listToString(vertexList) contains (vert2.getName))){
	      vertexList += vert2
	    }
	  }
	  vertexList
	}
  
	/****************************************************************
	 * Finds the shortest path from a vertex to another
	 ***************************************************************/
	def shortestPathDijkstra(startLoc: String, targetLoc: String, edges: List[Map[String, Any]]): Map[String, Any] = {
	  
	  var unvisited = dataToGraph(edges)
	  
	  //Not checked to see if start/end locations are in edge list
	  var start = startLoc
	  var end = targetLoc
	  var neighbors: ArrayBuffer[Vertex] = ArrayBuffer.empty
	  var visited: ArrayBuffer[Vertex] = ArrayBuffer.empty
	  var weight: ArrayBuffer[Int] = ArrayBuffer.empty
	  var previous: ArrayBuffer[Vertex] = ArrayBuffer.empty
	  var index = 0
	  var current: Vertex = null
	  var tempDist = 0
	  var finished = false
	  var isRemoved = false
	  
	  //gets start location set up
	  unvisited.foreach((vert) => {
	    if(!isRemoved){
	      if(vert.getName.equals(start)){
	        vert.setDistance(0)
	      
	        remove(vert, unvisited)
	        isRemoved = true
		  
		    current = vert
	    }
	  }
	  })
		  
	  //grabs neighbors info
	  while(!finished){
		  neighbors = ArrayBuffer.empty
		  weight = ArrayBuffer.empty
		  previous = ArrayBuffer.empty
		  
		  edgeList.foreach((edge) => {
		    var tempStr = ""
		    visited.foreach((vert) => {
		      tempStr += vert.getName + " "
		    })
		    
		    if(edge.getSideA.getName.equals(current.getName) && !tempStr.contains(edge.getSideB.getName)){
		      neighbors += updateFromList(edge.getSideB, unvisited)
		      weight += edge.getWeight
		    }
		    else if(edge.getSideB.getName.equals(current.getName) && !tempStr.contains(edge.getSideA.getName)){
		      neighbors += updateFromList(edge.getSideA, unvisited)
		      weight += edge.getWeight
		    }
		  })
		  
		  index = 0

		  //begins Dijkstra's algorithm
		  neighbors.foreach((n) => {
	    
		    tempDist = current.getDistance + weight(index)

		    if(tempDist < n.getDistance){
		      n.setDistance(tempDist)
		      n.setPreviousVertex(current)
		    }
		    
			  index += 1
			  updateToList(n, unvisited)
		  })
		  
		  unvisited -= current
		  visited += current
		  
		  visited.foreach((vert) => {
		    if(vert.equalsName(end)){
		      finished = true
		    }
		  })
		  current = lowestDistance(unvisited)
	}
	  
	  //maps vertices
	  var str = ""
	  var dist = 0
	  var curr: Vertex = null
	  visited.foreach((vert) => {
	    if(vert.getName.equals(end)){
	      curr = vert
	      dist = curr.getDistance
	      
	      while(!curr.getName.equals(start)){
	        str = curr.getPreviousVertex.getName + " => " + str
	        curr = curr.getPreviousVertex
	      }
	      str += vert.getName
	    }
	  })
	  
	  var map = Map("\"distance\"" -> dist, "\"path\"" -> ("\"" + str + "\""))

	  map
	}
}