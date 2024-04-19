import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class Board2Test {

    @Test
    public void testIndex(){
        int oldReach = 12;
        int newReach = 13;
        int x = -12;
        int y = -12;
        int index = Board2.indexFor(x, y, oldReach);
        System.out.println("index = " + index);
        int newIndex = Board2.newIndexFor(index, newReach, oldReach);
        int expectedIndex = Board2.indexFor(-12,-12,13);
        assertEquals(expectedIndex, newIndex);

    }

}
