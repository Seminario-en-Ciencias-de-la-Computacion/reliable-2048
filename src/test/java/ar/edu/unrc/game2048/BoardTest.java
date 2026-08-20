package ar.edu.unrc.game2048;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BoardTest {
    // Helpers
    private void resetBoard(Board b1){
        Cell aux =  new Cell(0);
        for(int i = 0; i < b1.getSize(); i++){
            for(int j = 0 ; j < b1.getSize(); j++){
                b1.setCell(i, j, aux);
            }
        }
    }

    // Tests
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

    @Test
    public void testMoveDown(){
        Board board1 = new Board();
        resetBoard(board1);
        board1.setCell(0, 0, new Cell(2));
        board1.setCell(1, 0, new Cell(2));
        board1.moveDown();
        assertEquals(board1.getCell(3,0).getValue(), 4);
    }

    @Test
    public void testMoveDown2(){
        Board board1 = new Board();
        resetBoard(board1);
        board1.setCell(0, 0, new Cell(2));
        board1.setCell(1, 0, new Cell(8));
        board1.moveDown();
        assertEquals(board1.getCell(3,0).getValue(), 8);
    }


    @Test
    public void testMoveUP(){
        Board board1 = new Board();
        resetBoard(board1);
        board1.setCell(0, 0, new Cell(2));
        board1.setCell(1, 0, new Cell(2));
        board1.moveUp();
        assertEquals(board1.getCell(0,0).getValue(), 4);
    }

    @Test
    public void testMoveUP2(){
        Board board1 = new Board();
        resetBoard(board1);
        board1.setCell(0, 0, new Cell(2));
        board1.setCell(1, 0, new Cell(16));
        board1.moveUp();
        assertEquals(board1.getCell(0,0).getValue(), 2);
    }

    @Test
    public void testMoveUP3(){
        Board board1 = new Board();
        resetBoard(board1);
        board1.setCell(2, 0, new Cell(2));
        board1.setCell(3, 0, new Cell(2));
        board1.moveUp();
        assertEquals(board1.getCell(0,0).getValue(), 4);
    }

    @Test
    public void testMoveleft(){
        Board board1 = new Board();
        resetBoard(board1);
        board1.setCell(0, 3, new Cell(16));
        board1.setCell(0, 2, new Cell(16));
        board1.moveLeft();
        assertEquals(board1.getCell(0,0).getValue(), 32);
    }

    @Test
    public void testMoveleft2(){
        Board board1 = new Board();
        resetBoard(board1);
        board1.setCell(0, 3, new Cell(16));
        board1.setCell(0, 2, new Cell(4));
        board1.moveLeft();
        assertEquals(board1.getCell(0,0).getValue(), 4);
    }

    @Test
    public void testMoveRight(){
        Board board1 = new Board();
        resetBoard(board1);
        board1.setCell(0, 3, new Cell(16));
        board1.setCell(0, 2, new Cell(16));
        board1.moveRight();
        assertEquals(board1.getCell(0,3).getValue(), 32);
    }

    @Test
    public void testMoveRight2(){
        Board board1 = new Board();
        resetBoard(board1);
        board1.setCell(0, 3, new Cell(16));
        board1.setCell(0, 2, new Cell(2));
        board1.moveRight();
        assertEquals(board1.getCell(0,3).getValue(), 16);
    }
    // TESTS PARA CHEQUEAR MOVIMIENTOS INVÁLIDOS
    @Test 
    public void testMoverIzquierdaYArriba() {
        Board board = new Board();
        resetBoard(board);
        Cell celda = new Cell(16);
        board.setCell(0, 0, celda);

        assertFalse(board.moveLeft());
        assertFalse(board.moveUp());
        assertEquals(board.getCell(0, 0), celda);
    }

    @Test 
    public void testMoverDerechaYAbajo() {
        Board board = new Board();
        resetBoard(board);
        Cell celda = new Cell(16);
        board.setCell(3, 3, celda);

        assertFalse(board.moveRight());
        assertFalse(board.moveDown());
        assertEquals(board.getCell(3, 3), celda);
    }

    // TESTS PARA WINNING Y LOSING BOARD
    @Test
    public void testWinningBoard1(){
        Board board = new Board();
        assertFalse(board.isWinningBoard());
        board.setCell(2, 3,new Cell(Board.WINNING_VALUE));
        assertTrue(board.isWinningBoard());
    }
 
    @Test
    public void testWinningAndLosingBoard(){
        Board board = new Board();
        assertFalse(board.isLosingBoard());

        // Relleno tablero para crear posicion perdedora
        int[][] values = {
            {2, 4, 2, 4},
            {4, 2, 4, 2},
            {2, 4, 2, 4},
            {4, 2, 4, Board.WINNING_VALUE}
        };

        for (int i = 0; i < board.getSize(); i++){
            for (int j = 0; j < board.getSize(); j++){
                board.setCell(i, j, new Cell(values[i][j]));
            }
        }
        
        assertTrue(board.isWinningBoard());
        assertFalse(board.isLosingBoard());
    }

    @Test
    public void testLosingBoard1(){
        Board board = new Board();
        assertFalse(board.isLosingBoard());

        // Relleno tablero para crear posicion perdedora
        int[][] values = {
            {2, 4, 2, 4},
            {4, 2, 4, 2},
            {2, 4, 2, 4},
            {4, 2, 4, 2}
        };

        for (int i = 0; i < board.getSize(); i++){
            for (int j = 0; j < board.getSize(); j++){
                board.setCell(i, j, new Cell(values[i][j]));
            }
        }
        
        assertTrue(board.isLosingBoard());
    }

    @Test
    public void testLosingBoard2(){
        Board board = new Board();

        // Relleno tablero para crear posicion
        int[][] values = {
            {2, 4, 2, 4},
            {4, 2, 4, 2},
            {2, 4, 2, 4},
            {4, 2, 8, 8}
        };

        for (int i = 0; i < board.getSize(); i++){
            for (int j = 0; j < board.getSize(); j++){
                board.setCell(i, j, new Cell(values[i][j]));
            }
        }
        
        assertFalse(board.isLosingBoard());
    }

    @Test
    public void testLosingBoard3(){
        Board board = new Board();

        // Relleno tablero para crear posicion
        int[][] values = {
            {2, 8, 2, 4},
            {4, 8, 4, 2},
            {2, 4, 2, 4},
            {4, 2, 4, 2}
        };

        for (int i = 0; i < board.getSize(); i++){
            for (int j = 0; j < board.getSize(); j++){
                board.setCell(i, j, new Cell(values[i][j]));
            }
        }
        
        assertFalse(board.isLosingBoard());
    }

    @Test
    public void testLosingBoard4() {
        Board board = new Board();

        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                board.setCell(row, col, new Cell(2));
            }
        }

        board.setCell(2, 2, new Cell(0));

        assertFalse(board.isLosingBoard());
    }

    @Test 
    public void testHasEmptyCells() {
        // Este test también prueba el método getEmptyPositions(), ya que hasEmptyCells() lo usa internamente.
        Board board = new Board();
        assertTrue(board.hasEmptyCells());

        for (int i = 0; i < board.getSize(); i++){
            for (int j = 0; j < board.getSize(); j++){
                board.setCell(i, j, new Cell(4));
            }
        }

        assertFalse(board.hasEmptyCells());
        board.setCell(0, 3, new Cell(0));
        assertTrue(board.hasEmptyCells());
    }

    @Test
    public void testIsFull(){
        Board board = new Board();
        assertTrue(board.hasEmptyCells());

        for (int i = 0; i < board.getSize(); i++){
            for (int j = 0; j < board.getSize(); j++){
                board.setCell(i, j, new Cell(4));
            }
        }

        assertTrue(board.isFull());
    }

    @Test
    public void testScore(){
        Board board = new Board();
        resetBoard(board);

        board.setCell(0, 0, new Cell(2));
        board.setCell(1, 0, new Cell(2));

        board.moveUp();

        assertEquals(4, board.getScore());
    }

    @Test
    public void testScore2(){
        Board board = new Board();
        resetBoard(board);

        board.setCell(0, 0, new Cell(2));
        board.setCell(1, 0, new Cell(2));
        board.setCell(2, 0, new Cell(4));
        board.setCell(3, 0, new Cell(4));

        board.moveUp();

        assertEquals(12, board.getScore());
    }

    @Test
    public void testScore3(){
        Board board = new Board();
        resetBoard(board);

        board.setCell(0, 0, new Cell(2));
        board.setCell(1, 0, new Cell(4));

        board.moveUp();

        assertEquals(0, board.getScore());
    }
}
