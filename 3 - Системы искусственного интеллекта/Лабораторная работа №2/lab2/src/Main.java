import informed_search.AStarSearch;
import informed_search.BestFirstSearch;
import uninformed_search.*;


public class Main {
    public static void main(String[] args) {
        DepthFirstSearch depthFirstSearch = new DepthFirstSearch();
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
        DepthLimitSearch depthLimitSearch = new DepthLimitSearch();
        BestFirstSearch bestFirstSearch = new BestFirstSearch();
        BidirectionalSearch bidirectionalSearch = new BidirectionalSearch();

        System.out.println("Поиск в глубину (DFS): ");
        depthFirstSearch.setFinal_point("Житомир");
        depthFirstSearch.dfs("С.Петербург");
        System.out.println(depthFirstSearch.getAnswer());


        System.out.println("\nПоиск в ширину (BFS): ");
        breadthFirstSearch.setFinal_vertex("Житомир");
        breadthFirstSearch.bfs_main("С.Петербург");


        System.out.println("\nПоиск с ограниченем глубины: ");
        depthLimitSearch.setLimit(3);
        depthLimitSearch.setFinal_point("Житомир");
        depthLimitSearch.dfs("С.Петербург");
        System.out.println(depthLimitSearch.getAnswer());


        System.out.println("\nПоиск с итеративным углублением: ");
        for (int i = 1; i <= 5; i++) {
            DepthLimitSearch depthLimitSearch1 = new DepthLimitSearch();
            depthLimitSearch1.setLimit(i);
            depthLimitSearch1.setFinal_point("Житомир");
            depthLimitSearch1.dfs("С.Петербург");
            System.out.println("Результат при лимите = " + i + ": " + depthLimitSearch1.getAnswer());
        }

        System.out.println("\nДвунаправленный поиск: ");
        if (!bidirectionalSearch.bidirectional_search("С.Петербург", "Житомир"))
            System.out.println("Путь не существует");


        System.out.println("\nЖадный поиск по первому наилучшему соответствию: ");
        bestFirstSearch.setFinal_point("Житомир");
        bestFirstSearch.bestFirstSearch("С.Петербург");
        System.out.println(bestFirstSearch.getAnswer());


        System.out.println("\nАлгоритм A*: ");
        AStarSearch aStarSearch = new AStarSearch();
        aStarSearch.aStarMethod("С.Петербург", "Житомир");
    }


}
