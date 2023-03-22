package uninformed_search;

import java.util.*;

public class BidirectionalSearch {
    private Map<String, LinkedList<String>> neighbors;


    public BidirectionalSearch() {
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


    public void bfs(Queue<String> queue, Map<String, Boolean> visited, Map<String, String> parent) {
        String current = queue.poll();
        Iterator<String> ite = neighbors.get(current).listIterator();
        while (ite.hasNext()) {
            String neighbor = ite.next();
            if (!visited.get(neighbor)) {
                parent.put(neighbor, current);
                visited.put(neighbor, true);
                queue.add(neighbor);
            }
        }
    }

    public String intersectionCheck(Map<String, Boolean> v1_noted,
                                    Map<String, Boolean> v2_noted) {

        for (Map.Entry<String, Boolean> entry : v1_noted.entrySet()) {
            for (Map.Entry<String, Boolean> entry2 : v2_noted.entrySet()) {
                if (entry.getKey() == entry2.getKey() && entry.getValue() && entry2.getValue())
                    return entry.getKey();
            }
        }
        return null;
    }


    public boolean bidirectional_search(String v1, String v2) {
        Map<String, Boolean> v1_noted = new HashMap<>();
        Map<String, Boolean> v2_noted = new HashMap<>();

        Map<String, String> v1_parent = new HashMap<>();
        Map<String, String> v2_parent = new HashMap<>();


        Queue<String> v1_queue = new LinkedList<String>();
        Queue<String> v2_queue = new LinkedList<String>();

        for (Map.Entry<String, LinkedList<String>> entry : neighbors.entrySet()) {
            v1_noted.put(entry.getKey(), false);
            v2_noted.put(entry.getKey(), false);
        }

        v1_queue.add(v1);
        v1_noted.put(v1, true);
        v1_parent.put(v1, null);

        v2_queue.add(v2);
        v2_noted.put(v2, true);
        v2_parent.put(v2, null);


        while (!v1_queue.isEmpty() && !v2_queue.isEmpty()) {
            bfs(v1_queue, v1_noted, v1_parent);
            bfs(v2_queue, v2_noted, v2_parent);


            String intersectResult = intersectionCheck(v1_noted, v2_noted);

            if (intersectResult != null) {

                String final_answer = "";
                final_answer += intersectResult;

                String current_vertex = intersectResult;
                while (current_vertex != v1) {
                    final_answer = v1_parent.get(current_vertex) + " " + final_answer;
                    current_vertex = v1_parent.get(current_vertex);
                }

                current_vertex = intersectResult;
                while (current_vertex != v2) {
                    final_answer += " " + v2_parent.get(current_vertex);
                    current_vertex = v2_parent.get(current_vertex);
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

}
