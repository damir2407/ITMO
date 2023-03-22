package informed_search;

import java.util.*;

public class BestFirstSearch {
    private Map<String, Boolean> noted;
    private String final_vertex;
    private Map<String, LinkedList<String>> neighbors;
    private Map<String, Integer> h;
    private String answer = "";

    public BestFirstSearch() {
        noted = new HashMap<>();
        neighbors = new HashMap<>();
        h = new HashMap<>();

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


    }


    public void addNeighbors(String vertex1, String vertex2) {
        if (neighbors.get(vertex1) == null) neighbors.put(vertex1, new LinkedList<>());
        if (neighbors.get(vertex2) == null) neighbors.put(vertex2, new LinkedList<>());


        neighbors.get(vertex1).push(vertex2);
        neighbors.get(vertex2).push(vertex1);

        noted.put(vertex1, false);
        noted.put(vertex2, false);
    }


    public void bestFirstSearch(String start_vertex) {
        Deque<String> deque = new ArrayDeque<>();
        deque.push(start_vertex);
        answer = start_vertex + " ";
        noted.put(start_vertex, true);

        while (!deque.isEmpty()) {
            String v = deque.getFirst();
            deque.pollFirst();
            Iterator<String> ite = neighbors.get(v).listIterator();
            Integer min = 100000;
            String minNeighbor = "";

            while (ite.hasNext()) {
                String neighbor = ite.next();
                if (noted.get(neighbor)) continue;

                if (neighbor.equals(final_vertex)) {
                    noted.put(final_vertex, true);
                    return;
                }
                if (h.get(neighbor) < min) {
                    min = h.get(neighbor);
                    minNeighbor = neighbor;
                }
            }
            answer += minNeighbor + " ";
            noted.put(minNeighbor, true);
            deque.push(minNeighbor);


        }

    }


    public void setFinal_point(String final_vertex) {
        this.final_vertex = final_vertex;
    }

    public String getAnswer() {
        if (noted.get(final_vertex) == false) return "Невозможно найти заданный путь: " + answer;
        return this.answer + " " + final_vertex;
    }
}
