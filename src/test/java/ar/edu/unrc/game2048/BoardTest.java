package ar.edu.unrc.game2048;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BoardTest {
    

    @Test
    public void testBoardCreation(){
        Board board1 = new Board();
        Board board2 = new Board(4);
        assertEquals(board1.getSize(), board2.getSize());
    }

    @Test
    public void testBoardCreation2(){
        assertThrows(IllegalArgumentException.class, () -> new Board(-1));
    }

    @Test
    public void testBoardCreation3(){
        Board board1 = new Board();
        Board board2 = new Board(board1);
        assertEquals(board1, board2);
    }

    @Test
    public void testGetSize(){
        Board board1 = new Board(8);
        assertEquals(board1.getSize(), 8);
    }

    @Test
    public void testGetEmptyPositions(){
        Board board1 = new Board(10);
        int aux = board1.getSize();
        assertEquals(board1.getEmptyPositions().size(), aux * aux -2);
    }

    @Test
    public void testGetCell(){
        Board board1 = new Board(3);
        assertThrows(IndexOutOfBoundsException.class,() ->  board1.getCell(5, 5));
    }

    private void resetBoard(Board b1){
        Cell aux =  new Cell(0);
        for(int i = 0; i < b1.getSize(); i++){
            for(int j = 0 ; j < b1.getSize(); j++){
                b1.setCell(i, j, aux);
            }
        }
    }

    @Test
    public void testMoveDown(){
        Board board1 = new Board();
        resetBoard(board1);
        board1.setCell(3, 3, new Cell(2));
        board1.setCell(2, 3, new Cell(2));
        board1.moveDown();
        assertEquals(board1.getCell(3,3).getValue(), 4);
    }
}
