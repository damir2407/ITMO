package test.java.task2;

import main.java.task2.BreadCrumbs;
import main.java.task2.QuickSort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuickSortTest {
    static QuickSort quickSort;

    @BeforeAll
    static void init() {
        quickSort = new QuickSort(new BreadCrumbs());
    }

    @Test
    @DisplayName("Обычный тест сортировки")
    void sort() {
        int[] arr = {5, 2, 7, 3, 9, 8, 1, 6, 4};
        int[] arrSort = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        quickSort.sort(arr, 0, arr.length - 1);
        Assertions.assertEquals(quickSort.getCrumbs().getTrace(),"l0h10l0h-1l1h10l1h4l1h3l1h0l2h3l2h1l3h3l5h4l6h10l6h7l6h5l7h7l9h10l9h8l10h10l0h11l0h10l0h9l0h2l0h-1l1h2l1h0l2h2l4h9l4h3l5h9l5h7l5h4l6h7l6h5l7h7l9h9l11h10l12h11l0h8l0h2l0h-1l1h2l1h0l2h2l4h8l4h3l5h8l5h7l5h4l6h7l6h6l8h7l9h8","Этап выполнения не совпадает");
        Assertions.assertArrayEquals(arrSort, arr,"Массив отсортирован не верно");
    }

    @Test
    @DisplayName("Сортировка отрицательных чисел")
    void negativeSort() {
        int[] arr = {-5, -2, -7, -3, -9, -8, -1, -6, -4};
        int[] arrSort = {-9, -8, -7, -6, -5, -4, -3, -2, -1};
        quickSort.sort(arr, 0, arr.length - 1);
        Assertions.assertEquals(quickSort.getCrumbs().getTrace(),"l0h10l0h-1l1h10l1h4l1h3l1h0l2h3l2h1l3h3l5h4l6h10l6h7l6h5l7h7l9h10l9h8l10h10l0h11l0h10l0h9l0h2l0h-1l1h2l1h0l2h2l4h9l4h3l5h9l5h7l5h4l6h7l6h5l7h7l9h9l11h10l12h11l0h8l0h2l0h-1l1h2l1h0l2h2l4h8l4h3l5h8l5h7l5h4l6h7l6h6l8h7l9h8l0h8l0h4l0h2l0h0l2h2l4h4l6h8l6h5l7h8l7h7l9h8","Этап выполнения не совпадает");
        Assertions.assertArrayEquals(arrSort, arr,"Массив отсортирован не верно");
    }

    @Test
    @DisplayName("Сортировка с повторяющимися числами")
    void checkRepetitions() {
        int[] arr = {1, 2, 1, 0, 0, 0, 1, 2, 1, 1, 94, 1231};
        int[] arrSort = {0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 94, 1231};
        quickSort.sort(arr, 0, arr.length - 1);
        Assertions.assertEquals(quickSort.getCrumbs().getTrace(),"l0h10l0h-1l1h10l1h4l1h3l1h0l2h3l2h1l3h3l5h4l6h10l6h7l6h5l7h7l9h10l9h8l10h10l0h11l0h10l0h9l0h2l0h-1l1h2l1h0l2h2l4h9l4h3l5h9l5h7l5h4l6h7l6h5l7h7l9h9l11h10l12h11","Этап выполнения не совпадает");

        Assertions.assertArrayEquals(arrSort, arr,"Массив отсортирован не верно");
    }

    @Test
    @DisplayName("Сортировка с граничными числами ")
    void checkMinAndMaxValue() {
        int[] arr = {5, 2, 7, 3, 9, 8, 1, 6, 4, Integer.MAX_VALUE, Integer.MIN_VALUE};
        int[] arrSort = {Integer.MIN_VALUE, 1, 2, 3, 4, 5, 6, 7, 8, 9, Integer.MAX_VALUE};
        quickSort.sort(arr, 0, arr.length - 1);
        Assertions.assertEquals(quickSort.getCrumbs().getTrace(),"l0h10l0h-1l1h10l1h4l1h3l1h0l2h3l2h1l3h3l5h4l6h10l6h7l6h5l7h7l9h10l9h8l10h10","Этап выполнения не совпадает");
        Assertions.assertArrayEquals(arrSort, arr,"Массив отсортирован не верно");
    }

    @Test
    @DisplayName("Сортировка сортированного массива")
    void checkAlreadySortedTest() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] arrSort = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        quickSort.sort(arr, 0, arr.length - 1);
        Assertions.assertEquals(quickSort.getCrumbs().getTrace(),"l0h10l0h-1l1h10l1h4l1h3l1h0l2h3l2h1l3h3l5h4l6h10l6h7l6h5l7h7l9h10l9h8l10h10l0h11l0h10l0h9l0h2l0h-1l1h2l1h0l2h2l4h9l4h3l5h9l5h7l5h4l6h7l6h5l7h7l9h9l11h10l12h11l0h8l0h2l0h-1l1h2l1h0l2h2l4h8l4h3l5h8l5h7l5h4l6h7l6h6l8h7l9h8l0h8l0h4l0h2l0h0l2h2l4h4l6h8l6h5l7h8l7h7l9h8l4h8l4h4l6h8l6h6l8h8l0h8l0h7l0h6l0h5l0h4l0h3l0h2l0h1l0h0l2h1l3h2l4h3l5h4l6h5l7h6l8h7l9h8","Этап выполнения не совпадает");
        Assertions.assertArrayEquals(arrSort, arr,"Массив отсортирован не верно");
    }

    @Test
    @DisplayName("Сортировка массива по возрастанию, который отсортирован по убыванию")
    void checkReversedTest() {
        int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] arrSort = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        quickSort.sort(arr, 0, arr.length - 1);
        Assertions.assertEquals(quickSort.getCrumbs().getTrace(),"l0h10l0h-1l1h10l1h4l1h3l1h0l2h3l2h1l3h3l5h4l6h10l6h7l6h5l7h7l9h10l9h8l10h10l0h11l0h10l0h9l0h2l0h-1l1h2l1h0l2h2l4h9l4h3l5h9l5h7l5h4l6h7l6h5l7h7l9h9l11h10l12h11l0h8l0h2l0h-1l1h2l1h0l2h2l4h8l4h3l5h8l5h7l5h4l6h7l6h6l8h7l9h8l0h8l0h4l0h2l0h0l2h2l4h4l6h8l6h5l7h8l7h7l9h8l4h8l4h4l6h8l6h6l8h8l0h8l0h7l0h6l0h5l0h4l0h3l0h2l0h1l0h0l2h1l3h2l4h3l5h4l6h5l7h6l8h7l9h8l0h8l0h-1l1h8l1h7l1h0l2h7l2h6l2h1l3h6l3h5l3h2l4h5l4h4l6h5l7h6l8h7l9h8","Этап выполнения не совпадает");
        Assertions.assertArrayEquals(arrSort, arr,"Массив отсортирован не верно");
    }
    @Test
    @DisplayName("Сортировка части массива")
    void partSortTest() {
        int[] arr = {5, 2, 7, 3, 9, 8, 1, 6, 4};
        int[] arrSort = {5, 2, 7, 3, 1, 4, 6, 8, 9};
        quickSort.sort(arr, 4, arr.length - 1);
        Assertions.assertEquals(quickSort.getCrumbs().getTrace(),"l0h10l0h-1l1h10l1h4l1h3l1h0l2h3l2h1l3h3l5h4l6h10l6h7l6h5l7h7l9h10l9h8l10h10l0h11l0h10l0h9l0h2l0h-1l1h2l1h0l2h2l4h9l4h3l5h9l5h7l5h4l6h7l6h5l7h7l9h9l11h10l12h11l0h8l0h2l0h-1l1h2l1h0l2h2l4h8l4h3l5h8l5h7l5h4l6h7l6h6l8h7l9h8l0h8l0h4l0h2l0h0l2h2l4h4l6h8l6h5l7h8l7h7l9h8l4h8l4h4l6h8l6h6l8h8","Этап выполнения не совпадает");
        Assertions.assertArrayEquals(arrSort, arr,"Массив отсортирован не верно");
    }
}

