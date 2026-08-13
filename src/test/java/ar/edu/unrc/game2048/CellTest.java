package ar.edu.unrc.game2048;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CellTest {

    @Test
    public void testCellPowerOfTwo(){
        assertThrows(IllegalArgumentException.class, () -> new Cell(3));
        assertThrows(IllegalArgumentException.class, () -> new Cell(6));
    }

    @Test
    public void testIsEmpty(){
        Cell cell1 = new Cell(0);
        assertTrue(cell1.isEmpty());
    }

    @Test
    public void testGetValue(){
        Cell cell1 = new Cell(8);
        assertEquals(8, cell1.getValue());
    }


    @Test
    public void testCanMerge(){
        Cell cell1 = new Cell(16);
        Cell cell2 = new Cell(16);
        assertTrue(cell1.canMergeWith(cell2));
    }

    @Test
    public void testCanMerge2(){
        Cell cell1 = new Cell(16);
        Cell cell2 = new Cell(4);
        assertFalse(cell1.canMergeWith(cell2));
    }

    @Test
    public void testMergeWith(){
        Cell cell1 = new Cell(32);
        Cell cell2 = new Cell(8);
        assertThrows(IllegalArgumentException.class, () -> cell1.mergeWith(cell2));
    }

}
