package uninformed_search;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class DepthLimitSearch {
    private Map<String, LinkedList<String>> neighbors;
    private Map<String, Boolean> noted;
    private String final_vertex;
    private String answer = "";
    private int limit = 0;
    private int depth = 0;

    public DepthLimitSearch() {
        neighbors = new HashMap<>();
        noted = new HashMap<>();

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

    public void addNeighbors(String vertex1, String vertex2) {
        if (neighbors.get(vertex1) == null) neighbors.put(vertex1, new LinkedList<>());
        if (neighbors.get(vertex2) == null) neighbors.put(vertex2, new LinkedList<>());

        neighbors.get(vertex1).push(vertex2);
        neighbors.get(vertex2).push(vertex1);

        noted.put(vertex1, false);
        noted.put(vertex2, false);

    }

    public void setFinal_point(String final_vertex) {
        this.final_vertex = final_vertex;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public boolean dfs(String vertex) {

        if (vertex.equals(this.final_vertex)) return true;
        noted.put(vertex, true);
        answer += vertex + "-> ";
        Iterator<String> ite = neighbors.get(vertex).listIterator();
        while (ite.hasNext()) {
            String neighbor = ite.next();
            if (!noted.get(neighbor)) {
                if (depth == limit) {
                    depth--;
                    noted.put(vertex, false);
                    return false;
                } else depth++;

                boolean result = dfs(neighbor);

                if (result) {
                    noted.put(final_vertex, true);
                    return true;
                } else {
                    answer += vertex + "-> ";
                    noted.put(vertex, false);
                }

            }

        }
        depth--;
        return false;
    }

    public String getAnswer() {
        if (noted.get(final_vertex) == false) return "Невозможно найти заданный путь: " + answer;
        return this.answer + " " + final_vertex;
    }


}
