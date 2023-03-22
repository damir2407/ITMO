package informed_search;

import java.util.*;

public class AStarSearch {
    private Map<String, Map<String, Integer>> distanceBetweenNeighbors;
    private Map<String, Integer> h;
    private Map<String, Double> g;
    private Map<String, String> parent;
    private Map<String, VertexInfo> mapOfNodes;


    class VertexInfo implements Comparable<VertexInfo> {

        private LinkedList<String> neighborsOfNode = new LinkedList<>();
        private Double f_of_vertex = Double.MAX_VALUE;
        private String vertexName;

        public VertexInfo(String neighbor) {
            this.neighborsOfNode.push(neighbor);
        }

        public LinkedList<String> getNeighborsOfNode() {
            return neighborsOfNode;
        }

        public Double getF_of_vertex() {
            return f_of_vertex;
        }

        public String getVertexName() {
            return vertexName;
        }

        public void setVertexName(String vertexName) {
            this.vertexName = vertexName;
        }

        public void setF_of_vertex(Double f_of_vertex) {
            this.f_of_vertex = f_of_vertex;
        }

        @Override
        public int compareTo(VertexInfo o) {
            return Double.compare(this.f_of_vertex, o.f_of_vertex);
        }
    }


    public AStarSearch() {
        h = new HashMap<>();
        g = new HashMap<>();
        distanceBetweenNeighbors = new HashMap<>();
        parent = new HashMap<>();
        mapOfNodes = new HashMap<>();

        addNeighbors("Вильнюс", "Брест", 531);
        addNeighbors("Витебск", "Вильнюс", 360);
        addNeighbors("Каунас", "Вильнюс", 102);
        addNeighbors("Киев", "Вильнюс", 734);
        addNeighbors("Вильнюс", "Даугавпилс", 211);
        addNeighbors("Калининград", "Вильнюс", 333);


        addNeighbors("Витебск", "Брест", 638);
        addNeighbors("Воронеж", "Витебск", 869);
        addNeighbors("Волгоград", "Витебск", 1455);
        addNeighbors("Витебск", "Ниж.Новгород", 911);
        addNeighbors("С.Петербург", "Витебск", 602);
        addNeighbors("Орел", "Витебск", 522);

        addNeighbors("Воронеж", "Волгоград", 581);
        addNeighbors("Ярославль", "Воронеж", 739);

        addNeighbors("Житомир", "Волгоград", 1493);

        addNeighbors("Калининград", "Брест", 699);
        addNeighbors("С.Петербург", "Калининград", 739);

        addNeighbors("Рига", "Каунас", 267);

        addNeighbors("Киев", "Житомир", 131);
        addNeighbors("Кишинев", "Киев", 467);
        addNeighbors("Одесса", "Киев", 487);
        addNeighbors("Харьков", "Киев", 471);

        addNeighbors("Житомир", "Донецк", 863);

        addNeighbors("Кишинев", "Донецк", 812);

        addNeighbors("С.Петербург", "Рига", 641);
        addNeighbors("Москва", "С.Петербург", 664);
        addNeighbors("Мурманск", "С.Петербург", 1412);

        addNeighbors("Москва", "Казань", 815);
        addNeighbors("Москва", "Ниж.Новгород", 411);
        addNeighbors("Москва", "Минск", 690);
        addNeighbors("Москва", "Донецк", 1084);
        addNeighbors("Орел", "Москва", 368);

        addNeighbors("Мурманск", "Минск", 2238);

        addNeighbors("Орел", "Донецк", 709);

        addNeighbors("Рига", "Таллинн", 308);

        addNeighbors("Харьков", "Симферополь", 639);

        addNeighbors("Ярославль", "Минск", 940);

        addNeighbors("Уфа", "Казань", 525);
        addNeighbors("Уфа", "Самара", 461);


        h.put("Вильнюс", 1000);
        h.put("Брест", 460);
        h.put("Витебск", 640);
        h.put("Воронеж", 870);
        h.put("Волгоград", 1400);
        h.put("Ниж.Новгород", 1500);
        h.put("Даугавпилс", 1200);
        h.put("Калининград", 970);
        h.put("Каунас", 940);
        h.put("Киев", 150);
        h.put("Житомир", 0);
        h.put("Донецк", 870);
        h.put("Кишинев", 450);
        h.put("С.Петербург", 1300);
        h.put("Рига", 1200);
        h.put("Москва", 1000);
        h.put("Казань", 1900);
        h.put("Минск", 510);
        h.put("Мурманск", 2600);
        h.put("Орел", 680);
        h.put("Одесса", 500);
        h.put("Таллинн", 1500);
        h.put("Харьков", 630);
        h.put("Симферополь", 1700);
        h.put("Ярославль", 1300);
        h.put("Уфа", 2200);
        h.put("Самара", 1800);


        g.put("Вильнюс", Double.MAX_VALUE);
        g.put("Брест", Double.MAX_VALUE);
        g.put("Витебск", Double.MAX_VALUE);
        g.put("Воронеж", Double.MAX_VALUE);
        g.put("Волгоград", Double.MAX_VALUE);
        g.put("Ниж.Новгород", Double.MAX_VALUE);
        g.put("Даугавпилс", Double.MAX_VALUE);
        g.put("Калининград", Double.MAX_VALUE);
        g.put("Каунас", Double.MAX_VALUE);
        g.put("Киев", Double.MAX_VALUE);
        g.put("Житомир", Double.MAX_VALUE);
        g.put("Донецк", Double.MAX_VALUE);
        g.put("Кишинев", Double.MAX_VALUE);
        g.put("С.Петербург", 0.0);
        g.put("Рига", Double.MAX_VALUE);
        g.put("Москва", Double.MAX_VALUE);
        g.put("Казань", Double.MAX_VALUE);
        g.put("Минск", Double.MAX_VALUE);
        g.put("Мурманск", Double.MAX_VALUE);
        g.put("Орел", Double.MAX_VALUE);
        g.put("Одесса", Double.MAX_VALUE);
        g.put("Таллинн", Double.MAX_VALUE);
        g.put("Харьков", Double.MAX_VALUE);
        g.put("Симферополь", Double.MAX_VALUE);
        g.put("Ярославль", Double.MAX_VALUE);
        g.put("Уфа", Double.MAX_VALUE);
        g.put("Самара", Double.MAX_VALUE);

    }


    public boolean aStarMethod(String v_start, String v_final) {
        PriorityQueue<VertexInfo> closedList = new PriorityQueue<>();
        PriorityQueue<VertexInfo> openList = new PriorityQueue<>();

        parent.put(v_start, "");
        mapOfNodes.get(v_start).setF_of_vertex(g.get(v_start) + h.get(v_start));
        openList.add(mapOfNodes.get(v_start));


        while (!openList.isEmpty()) {
            VertexInfo current_vertex = openList.peek();

            if (current_vertex.getVertexName().equals(v_final)) {

                String n = v_final;

                String final_answer = "";
                while (parent.get(n) != "") {
                    final_answer = n + " " + final_answer;
                    n = parent.get(n);
                }

                final_answer = n + " " + final_answer;
                System.out.println(final_answer);
                return true;
            }


            Iterator<String> ite = current_vertex.getNeighborsOfNode().listIterator();
            while (ite.hasNext()) {
                VertexInfo neighbor = mapOfNodes.get(ite.next());
                double distanceTraveled = g.get(current_vertex.getVertexName()) + distanceBetweenNeighbors.get(current_vertex.getVertexName()).get(neighbor.getVertexName());

                if (!openList.contains(neighbor) && !closedList.contains(neighbor)) {
                    parent.put(neighbor.getVertexName(), current_vertex.getVertexName());
                    g.put(neighbor.getVertexName(), distanceTraveled);
                    neighbor.setF_of_vertex((g.get(neighbor.getVertexName()) + h.get(neighbor.getVertexName())));
                    openList.add(neighbor);
                } else {
                    if (distanceTraveled < g.get(neighbor.getVertexName())) {
                        parent.put(neighbor.getVertexName(), current_vertex.getVertexName());
                        g.put(neighbor.getVertexName(), distanceTraveled);
                        neighbor.setF_of_vertex((g.get(neighbor.getVertexName()) + h.get(neighbor.getVertexName())));

                        closedList.remove(neighbor);
                        openList.add(neighbor);

                    }
                }


            }

            openList.remove(current_vertex);
            closedList.add(current_vertex);
        }
        return false;
    }

    public void addNeighbors(String vertex1, String vertex2, Integer distance_v1_to_v2) {

        if (distanceBetweenNeighbors.get(vertex1) == null) distanceBetweenNeighbors.put(vertex1, new HashMap<>());
        if (distanceBetweenNeighbors.get(vertex2) == null) distanceBetweenNeighbors.put(vertex2, new HashMap<>());

        if (mapOfNodes.get(vertex1) == null) {
            mapOfNodes.put(vertex1, new VertexInfo(vertex2));
            mapOfNodes.get(vertex1).setVertexName(vertex1);
        }
        if (mapOfNodes.get(vertex2) == null) {
            mapOfNodes.put(vertex2, new VertexInfo(vertex1));
            mapOfNodes.get(vertex2).setVertexName(vertex2);
        }


        distanceBetweenNeighbors.get(vertex1).put(vertex2, distance_v1_to_v2);
        distanceBetweenNeighbors.get(vertex2).put(vertex1, distance_v1_to_v2);

        mapOfNodes.get(vertex1).getNeighborsOfNode().push(vertex2);
        mapOfNodes.get(vertex2).getNeighborsOfNode().push(vertex1);

    }

}
