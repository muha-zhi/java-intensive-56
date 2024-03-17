package com.marseille.javaintensive;

import com.marseille.javaintensive.homeworks.first.CustomArrayList;
import com.marseille.javaintensive.homeworks.first.CustomList;
import org.hibernate.id.IntegralDataTypeHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class JavaIntensiveApplicationTests {

    private CustomList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new CustomArrayList<>();
        list.add(0, 2);
        list.add(1, 1);
        list.add(2, 4);
        list.add(3, 3);
        list.add(4, 8);
        list.add(5, 5);
    }


    @Test
    public void shouldAddElement() {

        assertEquals(6, list.size());

        list.add(6, 7);

        assertEquals(7, list.size());
    }

    @Test
    public void shouldGetElementByIndex() {

        assertEquals(2, list.get(0));
        assertEquals(1, list.get(1));
        assertEquals(4, list.get(2));
        assertEquals(3, list.get(3));
        assertEquals(8, list.get(4));
        assertEquals(5, list.get(5));


    }

    @Test
    public void shouldRemoveElementByIndex() {

        list.remove(2);
        assertEquals(5, list.size());

    }

    @Test
    public void shouldClearList() {

        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void shouldSortList() {

        list.sort(Comparator.naturalOrder());

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));

    }

}
