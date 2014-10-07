package dollDeliveryPrj

import org.junit.Test
import org.junit.Assert._
import scala.collection.mutable.ArrayBuffer

class TestingSuite {
  
  var vert1 = new Vertex("testVertex1")
	var vert2 = new Vertex("testVertex2")
	var vert3 = new Vertex("testVertex3")
	var vert4 = new Vertex("testVertex4")
	var vert5 = new Vertex("testVertex5")
	var vert6 = new Vertex("testVertex6")
	var vert7 = new Vertex("testVertex7")
	var vert8 = new Vertex("testVertex8")
	var edge1 = new Edges(vert1, vert2, 10)
	var vertexList = new ArrayBuffer[Vertex]
	var edgeList = new ArrayBuffer[Edges]

  @Test def equalsFalseVertex(){
	  assertFalse(vert1.equals(vert2))
	}
	
	@Test def equalsTrueVertex(){
	  vert1 = new Vertex("test")
	  vert2 = new Vertex("test")
	  assertTrue(vert1.equals(vert2))
	}
	
	@Test def equalsNameVertex(){
	  assertFalse(vert1.getName.equals(vert2.getName))
	  var vertTest = new Vertex("testVertex1")
	  assertTrue(vert1.getName.equals(vertTest.getName))
	}
	
	@Test def equalsNameString(){
	  vert1.setName("test")
	  assertTrue(vert1.equalsName("test"))
	}
	
	@Test def vertexEmptyList(){
	  assertTrue(shortestPath.listToString(vertexList).equals(""))
	  assertTrue(shortestPath.listToString(vertexList).isEmpty())
	}
  
	@Test def vertexListToString(){
	  vertexList += vert1
	  assertTrue(shortestPath.listToString(vertexList).contains(vert1.getName))
	}
	
	@Test def edgesEmptyList(){
	  assertTrue(shortestPath.listToString(vertexList).equals(""))
	  assertTrue(shortestPath.listToString(vertexList).isEmpty())
	}
	
	@Test def edgesListToString(){
	  edgeList += edge1
	  assertTrue(shortestPath.edgeListToString(edgeList).contains(edge1.getSideA.getName))
	  assertTrue(shortestPath.edgeListToString(edgeList).contains(edge1.getSideB.getName))
	}
	
	@Test def removeEmptyNoError(){
	  assertTrue(vertexList.isEmpty)
	  shortestPath.remove(vert1, vertexList)
	  assertTrue(vertexList.isEmpty)
	}
	
	@Test def removeOnlyOne(){
	  vertexList += vert1
	  vertexList += vert1
	  assertTrue(vertexList.size == 2)
	  shortestPath.remove(vert1, vertexList)
	  assertTrue(vertexList.size == 1)
	}
	
	@Test def removeSameTwice(){
	  vertexList += vert1
	  vertexList += vert1
	  assertTrue(vertexList.size == 2)
	  shortestPath.remove(vert1, vertexList)
	  shortestPath.remove(vert1, vertexList)
	  assertTrue(vertexList.size == 0)
	  assertTrue(vertexList.isEmpty)
	}
	
	@Test def lowestDistanceEmpty(){
	  assertTrue(vertexList.isEmpty)
	  assertNull(shortestPath.lowestDistance(vertexList))
	}
	
	@Test def lowestDistanceZero(){
	  vert1.setDistance(0)
	  vert2.setDistance(1)
	  vertexList += vert1
	  vertexList += vert2
	  assertTrue(shortestPath.lowestDistance(vertexList).getName.equals(vert1.getName))
	  assertFalse(shortestPath.lowestDistance(vertexList).getName.equals(vert2.getName))
	}
	
	@Test def lowestDistanceEqual(){
	  vert1.setDistance(5)
	  vert2.setDistance(5)
	  vertexList += vert1
	  vertexList += vert2
	  assertTrue(shortestPath.lowestDistance(vertexList).getName.equals(vert1.getName))
	  assertFalse(shortestPath.lowestDistance(vertexList).getName.equals(vert2.getName))
	}
	
	@Test def lowestDistanceByFirstAdded(){
	  vert1.setDistance(5)
	  vert2.setDistance(5)
	  vert3.setDistance(5)
	  vert4.setDistance(5)
	  vert5.setDistance(5)
	  vert6.setDistance(10)
	  vertexList += vert2
	  vertexList += vert1
	  vertexList += vert3
	  vertexList += vert4
	  vertexList += vert5
	  vertexList += vert6
	  assertTrue(shortestPath.lowestDistance(vertexList).getName.equals(vert2.getName))
	  assertFalse(shortestPath.lowestDistance(vertexList).getName.equals(vert1.getName))
	}
	
	@Test def dataToGraphSizeSmall(){
	  vertexList = shortestPath.dataToGraph(List(
  Map("startLocation" -> "Kruthika's abode", "endLocation" -> "Mark's crib", "distance" -> 9)))
  	  assertTrue(vertexList.size == 2)
	}
	
	@Test def dataToGraphSizeLarge(){
	  vertexList = shortestPath.dataToGraph(List(
  Map("startLocation" -> "Kruthika's abode", "endLocation" -> "Mark's crib", "distance" -> 9),
  Map("startLocation" -> "Kruthika's abode", "endLocation" -> "Greg's casa", "distance" -> 4),
  Map("startLocation" -> "Kruthika's abode", "endLocation" -> "Matt's pad", "distance" -> 18),
  Map("startLocation" -> "Kruthika's abode", "endLocation" -> "Brian's apartment", "distance" -> 8),
  Map("startLocation" -> "Brian's apartment", "endLocation" -> "Wesley's condo", "distance" -> 7),
  Map("startLocation" -> "Brian's apartment", "endLocation" -> "Cam's dwelling", "distance" -> 17),
  Map("startLocation" -> "Greg's casa", "endLocation" -> "Cam's dwelling", "distance" -> 13),
  Map("startLocation" -> "Greg's casa", "endLocation" -> "Mike's digs", "distance" -> 19),
  Map("startLocation" -> "Greg's casa", "endLocation" -> "Matt's pad", "distance" -> 14),
  Map("startLocation" -> "Wesley's condo", "endLocation" -> "Kirk's farm", "distance" -> 10),
  Map("startLocation" -> "Wesley's condo", "endLocation" -> "Nathan's flat", "distance" -> 11),
  Map("startLocation" -> "Wesley's condo", "endLocation" -> "Bryce's den", "distance" -> 6),
  Map("startLocation" -> "Matt's pad", "endLocation" -> "Mark's crib", "distance" -> 19),
  Map("startLocation" -> "Matt's pad", "endLocation" -> "Nathan's flat", "distance" -> 15),
  Map("startLocation" -> "Matt's pad", "endLocation" -> "Craig's haunt", "distance" -> 14),
  Map("startLocation" -> "Mark's crib", "endLocation" -> "Kirk's farm", "distance" -> 9),
  Map("startLocation" -> "Mark's crib", "endLocation" -> "Nathan's flat", "distance" -> 12),
  Map("startLocation" -> "Bryce's den", "endLocation" -> "Craig's haunt", "distance" -> 10),
  Map("startLocation" -> "Bryce's den", "endLocation" -> "Mike's digs", "distance" -> 9),
  Map("startLocation" -> "Mike's digs", "endLocation" -> "Cam's dwelling", "distance" -> 20),
  Map("startLocation" -> "Mike's digs", "endLocation" -> "Nathan's flat", "distance" -> 12),
  Map("startLocation" -> "Cam's dwelling", "endLocation" -> "Craig's haunt", "distance" -> 18),
  Map("startLocation" -> "Nathan's flat", "endLocation" -> "Kirk's farm", "distance" -> 3)))
  assertTrue(vertexList.size == 12)
	}
	
	@Test def dataToGraphVertexDistance(){
	  vertexList = shortestPath.dataToGraph(List(
  Map("startLocation" -> "Kruthika's abode", "endLocation" -> "Mark's crib", "distance" -> 9)))
  	  assertTrue(vertexList(0).getDistance == Int.MaxValue)
	}
	
	@Test def dataToGraphPreviousVertex(){
	   vertexList = shortestPath.dataToGraph(List(
  Map("startLocation" -> "Kruthika's abode", "endLocation" -> "Mark's crib", "distance" -> 9)))
  		assertNull(vertexList(0).getPreviousVertex)
	}
	
	@Test def dataToGraphGetPreviousNull(){
	  vertexList = shortestPath.dataToGraph(List(
  Map("startLocation" -> "Kruthika's abode", "endLocation" -> "Mark's crib", "distance" -> 9)))
  	  assertNull(vertexList(0).getPreviousVertex)
	}
	
	@Test def dataToGraphDuplicateEntry(){
	  vertexList = shortestPath.dataToGraph(List(
  Map("startLocation" -> "Kruthika's abode", "endLocation" -> "Mark's crib", "distance" -> 9),
  Map("startLocation" -> "Kruthika's abode", "endLocation" -> "Mark's crib", "distance" -> 9)))
	  assertTrue(vertexList.size == 2)
	}
	
	@Test def sPDstartEqualsEnd(){
	  var ret = shortestPath.shortestPathDijkstra("A", "A", List(
        Map("startLocation" -> "A", "endLocation" -> "B", "distance" -> 3),
        Map("startLocation" -> "A", "endLocation" -> "E", "distance" -> 4)))
        assertTrue(ret.toString.equals("Map(\"distance\" -> 0, \"path\" -> \"A\")"))
	}
	
	@Test def sPDsmall(){
	  var ret = shortestPath.shortestPathDijkstra("A", "B", List(
        Map("startLocation" -> "A", "endLocation" -> "B", "distance" -> 3)))
        assertTrue(ret.toString.equals("Map(\"distance\" -> 3, \"path\" -> \"A => B\")"))
	}
	
	@Test def sPDgivenTest(){
	  var ret = shortestPath.shortestPathDijkstra("Kruthika's abode", "Craig's haunt", List(
  Map("startLocation" -> "Kruthika's abode", "endLocation" -> "Mark's crib", "distance" -> 9),
  Map("startLocation" -> "Kruthika's abode", "endLocation" -> "Greg's casa", "distance" -> 4),
  Map("startLocation" -> "Kruthika's abode", "endLocation" -> "Matt's pad", "distance" -> 18),
  Map("startLocation" -> "Kruthika's abode", "endLocation" -> "Brian's apartment", "distance" -> 8),
  Map("startLocation" -> "Brian's apartment", "endLocation" -> "Wesley's condo", "distance" -> 7),
  Map("startLocation" -> "Brian's apartment", "endLocation" -> "Cam's dwelling", "distance" -> 17),
  Map("startLocation" -> "Greg's casa", "endLocation" -> "Cam's dwelling", "distance" -> 13),
  Map("startLocation" -> "Greg's casa", "endLocation" -> "Mike's digs", "distance" -> 19),
  Map("startLocation" -> "Greg's casa", "endLocation" -> "Matt's pad", "distance" -> 14),
  Map("startLocation" -> "Wesley's condo", "endLocation" -> "Kirk's farm", "distance" -> 10),
  Map("startLocation" -> "Wesley's condo", "endLocation" -> "Nathan's flat", "distance" -> 11),
  Map("startLocation" -> "Wesley's condo", "endLocation" -> "Bryce's den", "distance" -> 6),
  Map("startLocation" -> "Matt's pad", "endLocation" -> "Mark's crib", "distance" -> 19),
  Map("startLocation" -> "Matt's pad", "endLocation" -> "Nathan's flat", "distance" -> 15),
  Map("startLocation" -> "Matt's pad", "endLocation" -> "Craig's haunt", "distance" -> 14),
  Map("startLocation" -> "Mark's crib", "endLocation" -> "Kirk's farm", "distance" -> 9),
  Map("startLocation" -> "Mark's crib", "endLocation" -> "Nathan's flat", "distance" -> 12),
  Map("startLocation" -> "Bryce's den", "endLocation" -> "Craig's haunt", "distance" -> 10),
  Map("startLocation" -> "Bryce's den", "endLocation" -> "Mike's digs", "distance" -> 9),
  Map("startLocation" -> "Mike's digs", "endLocation" -> "Cam's dwelling", "distance" -> 20),
  Map("startLocation" -> "Mike's digs", "endLocation" -> "Nathan's flat", "distance" -> 12),
  Map("startLocation" -> "Cam's dwelling", "endLocation" -> "Craig's haunt", "distance" -> 18),
  Map("startLocation" -> "Nathan's flat", "endLocation" -> "Kirk's farm", "distance" -> 3)))
  assertTrue(ret.toString.equals("Map(\"distance\" -> 31, \"path\" -> \"Kruthika's abode => Brian's apartment => Wesley's condo => Bryce's den => Craig's haunt\")"))
	}
	
	@Test def sPDmyPath(){
	  var ret = shortestPath.shortestPathDijkstra("A", "K", List(
        Map("startLocation" -> "A", "endLocation" -> "B", "distance" -> 3),
        Map("startLocation" -> "A", "endLocation" -> "E", "distance" -> 4),
        Map("startLocation" -> "A", "endLocation" -> "G", "distance" -> 6),
        Map("startLocation" -> "B", "endLocation" -> "C", "distance" -> 2),
        Map("startLocation" -> "B", "endLocation" -> "D", "distance" -> 1),
        Map("startLocation" -> "B", "endLocation" -> "J", "distance" -> 3),
        Map("startLocation" -> "C", "endLocation" -> "E", "distance" -> 7),
        Map("startLocation" -> "C", "endLocation" -> "J", "distance" -> 2),
        Map("startLocation" -> "D", "endLocation" -> "G", "distance" -> 1),
        Map("startLocation" -> "D", "endLocation" -> "I", "distance" -> 2),
        Map("startLocation" -> "E", "endLocation" -> "F", "distance" -> 2),
        Map("startLocation" -> "F", "endLocation" -> "G", "distance" -> 2),
        Map("startLocation" -> "G", "endLocation" -> "H", "distance" -> 2),
        Map("startLocation" -> "H", "endLocation" -> "K", "distance" -> 1),
        Map("startLocation" -> "I", "endLocation" -> "K", "distance" -> 3)))
        assertTrue(ret.toString.equals("Map(\"distance\" -> 8, \"path\" -> \"A => B => D => G => H => K\")"))
	}
	
	@Test def sPDstraightLine(){
	  var ret = shortestPath.shortestPathDijkstra("1", "6", List(
	      Map("startLocation" -> "1", "endLocation" -> "2", "distance" -> 3),
	      Map("startLocation" -> "2", "endLocation" -> "3", "distance" -> 4),
	      Map("startLocation" -> "3", "endLocation" -> "4", "distance" -> 5),
	      Map("startLocation" -> "4", "endLocation" -> "5", "distance" -> 6),
	      Map("startLocation" -> "5", "endLocation" -> "6", "distance" -> 7)))
	      assertTrue(ret.toString.equals("Map(\"distance\" -> 25, \"path\" -> \"1 => 2 => 3 => 4 => 5 => 6\")"))
	}
	
	@Test def sPDallTie(){
	  var ret = shortestPath.shortestPathDijkstra("start", "end", List(
	      Map("startLocation" -> "start", "endLocation" -> "m1", "distance" -> 1),
	      Map("startLocation" -> "m1", "endLocation" -> "end", "distance" -> 9),
	      Map("startLocation" -> "start", "endLocation" -> "m2", "distance" -> 2),
	      Map("startLocation" -> "m2", "endLocation" -> "end", "distance" -> 8),
	      Map("startLocation" -> "start", "endLocation" -> "m3", "distance" -> 3),
	      Map("startLocation" -> "m3", "endLocation" -> "end", "distance" -> 7),
	      Map("startLocation" -> "start", "endLocation" -> "m4", "distance" -> 4),
	      Map("startLocation" -> "m4", "endLocation" -> "end", "distance" -> 6),
	      Map("startLocation" -> "start", "endLocation" -> "m5", "distance" -> 5),
	      Map("startLocation" -> "m5", "endLocation" -> "end", "distance" -> 5)))
	      assertTrue(ret.toString.equals("Map(\"distance\" -> 10, \"path\" -> \"start => m1 => end\")"))
	}
  
}