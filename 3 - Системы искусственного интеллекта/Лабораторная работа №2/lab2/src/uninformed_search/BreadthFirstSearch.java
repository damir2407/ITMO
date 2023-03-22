package uninformed_search;

import java.util.*;

public class BreadthFirstSearch {
    private Map<String, LinkedList<String>> neighbors;
    private String final_vertex = "";

    public BreadthFirstSearch() {
        neighbors = new HashMap<>();
        addNeighbors("Вильнюс", "Брест");
        addNeighbors("Витебск", "Вильнюс");
        addNeighbors("Каунас", "Вильнюс");
        addNeighbors("Киев", "Вильнюс");
        addNeighbors("Вильнюс", "Даугавпилс");
        addNeighbors("Калининград", "Вильнюс");


        addNeighbors("Витебск", "Брест");
        addNeighbors("Воронеж", "Витебск");
        addNeighbors("Волгоград", "Витебск");
        addNeighbors("Витебск", "Ниж.Новгород");
        addNeighbors("С.Петербург", "Витебск");
        addNeighbors("Орел", "Витебск");

        addNeighbors("Воронеж", "Волгоград");
        addNeighbors("Ярославль", "Воронеж");

        addNeighbors("Житомир", "Волгоград");

        addNeighbors("Калининград", "Брест");
        addNeighbors("С.Петербург", "Калининград");

        addNeighbors("Рига", "Каунас");

        addNeighbors("Киев", "Житомир");
        addNeighbors("Кишинев", "Киев");
        addNeighbors("Одесса", "Киев");
        addNeighbors("Харьков", "Киев");

        addNeighbors("Житомир", "Донецк");

        addNeighbors("Кишинев", "Донецк");

        addNeighbors("С.Петербург", "Рига");
        addNeighbors("Москва", "С.Петербург");
        addNeighbors("Мурманск", "С.Петербург");

        addNeighbors("Москва", "Казань");
        addNeighbors("Москва", "Ниж.Новгород");
        addNeighbors("Москва", "Минск");
        addNeighbors("Москва", "Донецк");
        addNeighbors("Орел", "Москва");

        addNeighbors("Мурманск", "Минск");

        addNeighbors("Орел", "Донецк");

        addNeighbors("Рига", "Таллинн");

        addNeighbors("Харьков", "Симферополь");

        addNeighbors("Ярославль", "Минск");

        addNeighbors("Уфа", "Казань");
        addNeighbors("Уфа", "Самара");
    }


    public boolean bfs(Queue<String> queue, Map<String, Boolean> visited, Map<String, String> parent) {
        String current = queue.poll();

        if (current == final_vertex) return true;

        Iterator<String> ite = neighbors.get(current).listIterator();
        while (ite.hasNext()) {
            String neighbor = ite.next();
            if (!visited.get(neighbor)) {
                parent.put(neighbor, current);
                visited.put(neighbor, true);
                queue.add(neighbor);
                if (neighbor == final_vertex) return true;
            }
        }
        return false;
    }


    public boolean bfs_main(String v1) {
        Map<String, Boolean> v1_noted = new HashMap<>();

        Map<String, String> v1_parent = new HashMap<>();

        Queue<String> v1_queue = new LinkedList<String>();

        for (Map.Entry<String, LinkedList<String>> entry : neighbors.entrySet()) {
            v1_noted.put(entry.getKey(), false);
        }

        v1_queue.add(v1);
        v1_noted.put(v1, true);
        v1_parent.put(v1, null);


        while (!v1_queue.isEmpty()) {
            boolean bfsResult = bfs(v1_queue, v1_noted, v1_parent);

            if (bfsResult == true) {
                String final_answer = "";
                final_answer += final_vertex;

                String current_vertex = final_vertex;
                while (current_vertex != v1) {
                    final_answer = v1_parent.get(current_vertex) + " " + final_answer;
                    current_vertex = v1_parent.get(current_vertex);
                }

                System.out.println(final_answer);

                return true;
            }

        }
        return false;
    }


    public void addNeighbors(String vertex1, String vertex2) {
        if (neighbors.get(vertex1) == null) neighbors.put(vertex1, new LinkedList<>());
        if (neighbors.get(vertex2) == null) neighbors.put(vertex2, new LinkedList<>());

        neighbors.get(vertex1).push(vertex2);
        neighbors.get(vertex2).push(vertex1);

    }

    public void setFinal_vertex(String final_vertex) {
        this.final_vertex = final_vertex;
    }


}
