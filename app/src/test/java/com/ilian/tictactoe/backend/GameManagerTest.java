package com.ilian.tictactoe.backend;

import com.ilian.tictactoe.backend.guiconnectors.Figure;
import com.ilian.tictactoe.backend.guiconnectors.Row;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameManagerTest {
    private GameManager gameManager3x3;
    private GameManager gameManager4x4;
    private GameManager gameManager5x5;

    @Before
    public void setUp() {
        gameManager3x3 = new GameManager();
        gameManager4x4 = new GameManager(4);
        gameManager5x5 = new GameManager(5);
    }

    @After
    public void tearDown() {
        gameManager3x3 = null;
        gameManager4x4 = null;
        gameManager5x5 = null;
    }

    @Test
    public void testMarkSpaceIndexOutOfBoundsException() {
        // Test #1 (3x3)
        assertThrows(IndexOutOfBoundsException.class, () -> {
            gameManager3x3.markSpace(null, 1, 3);
        });

        // Test #2 (4x4)
        assertThrows(IndexOutOfBoundsException.class, () -> {
            gameManager4x4.markSpace(Figure.CROSS, 100, -450);
        });

        // Test #3 (5x5)
        assertThrows(IndexOutOfBoundsException.class, () -> {
            gameManager5x5.markSpace(Figure.ZERO, -1, 3);
        });
    }

    @Test
    public void testFieldValueAfterMarkSpace() {
        // Test #1
        gameManager3x3.markSpace(Figure.CROSS, 0, 0);
        assertEquals(Figure.CROSS, gameManager3x3.getFigure(0, 0));

        // Test #2
        gameManager3x3.markSpace(Figure.ZERO, 1, 1);
        assertEquals(Figure.ZERO, gameManager3x3.getFigure(1, 1));

        //Test #3
        assertNull(gameManager3x3.getFigure(2, 2));
    }

    @Test
    public void testMarkSpaceReturnValue() {
        assertTrue(gameManager3x3.markSpace(Figure.CROSS, 1, 2));   // Test #1
        assertFalse(gameManager3x3.markSpace(Figure.CROSS,1, 2));   // Test #2
        assertFalse(gameManager3x3.markSpace(Figure.ZERO,1, 2));    // Test #3
        assertTrue(gameManager3x3.markSpace(Figure.ZERO,0, 0));     // Test #4
    }

    @Test
    public void testHorizontalWinner() {
        // Test #1 (3x3)
        for (int i = 0; i < 3; i++) {
            gameManager3x3.markSpace(Figure.CROSS, 1, i);
        }
        assertEquals(gameManager3x3.getWinnerInfo(), new WinnerInfo(Row.HORIZONTAL, Figure.CROSS, 0, 1, 2, 1));

        // Test #2 (4x4)
        for (int i = 0; i < 4; i++) {
            gameManager4x4.markSpace(Figure.ZERO, 3, i);
        }
        assertEquals(gameManager4x4.getWinnerInfo(), new WinnerInfo(Row.HORIZONTAL, Figure.ZERO, 0, 3, 3, 3));

        // Test #3 (5x5)
        for (int i = 0; i < 5; i++) {
            gameManager5x5.markSpace(Figure.CROSS, 4, i);
        }
        assertEquals(gameManager5x5.getWinnerInfo(), new WinnerInfo(Row.HORIZONTAL, Figure.CROSS, 0, 4, 4, 4));
    }

    @Test
    public void testVerticalWinner() {
        // Test #1 (3x3)
        for (int i = 0; i < 3; i++) {
            gameManager3x3.markSpace(Figure.CROSS, i, 0);
        }
        assertEquals(gameManager3x3.getWinnerInfo(), new WinnerInfo(Row.VERTICAL, Figure.CROSS, 0, 0, 0, 2));

        // Test #2 (4x4)
        for (int i = 0; i < 4; i++) {
            gameManager4x4.markSpace(Figure.ZERO, i, 2);
        }
        assertEquals(gameManager4x4.getWinnerInfo(), new WinnerInfo(Row.VERTICAL, Figure.ZERO, 2, 0, 2, 3));

        // Test #3 (5x5)
        for (int i = 0; i < 5; i++) {
            gameManager5x5.markSpace(Figure.CROSS, i, 4);
        }
        assertEquals(gameManager5x5.getWinnerInfo(), new WinnerInfo(Row.VERTICAL, Figure.CROSS, 4, 0, 4, 4));
    }

    @Test
    public void testDownwardsDiagonalWinner() {
        // Test #1 (3x3)
        for (int i = 0; i < 3; i++) {
            gameManager3x3.markSpace(Figure.CROSS, i, i);
        }
        assertEquals(gameManager3x3.getWinnerInfo(), new WinnerInfo(Row.DOWNWARDS_DIAGONAL, Figure.CROSS, 0, 0, 2, 2));

        // Test #2 (4x4)
        for (int i = 0; i < 4; i++) {
            gameManager4x4.markSpace(Figure.ZERO, i, i);
        }
        assertEquals(gameManager4x4.getWinnerInfo(), new WinnerInfo(Row.DOWNWARDS_DIAGONAL, Figure.ZERO, 0, 0, 3, 3));

        // Test #3 (5x5)
        for (int i = 0; i < 5; i++) {
            gameManager5x5.markSpace(Figure.CROSS, i, i);
        }
        assertEquals(gameManager5x5.getWinnerInfo(), new WinnerInfo(Row.DOWNWARDS_DIAGONAL, Figure.CROSS, 0, 0, 4, 4));
    }

    @Test
    public void testUpwardsDiagonalWinner() {
        // Test #1 (3x3)
        for (int i = 0; i < 3; i++) {
            gameManager3x3.markSpace(Figure.CROSS, 2 - i, i);
        }
        assertEquals(gameManager3x3.getWinnerInfo(), new WinnerInfo(Row.UPWARDS_DIAGONAL, Figure.CROSS, 0, 2, 2, 0));

        // Test #2 (4x4)
        for (int i = 0; i < 4; i++) {
            gameManager4x4.markSpace(Figure.ZERO, 3 - i, i);
        }
        assertEquals(gameManager4x4.getWinnerInfo(), new WinnerInfo(Row.UPWARDS_DIAGONAL, Figure.ZERO, 0, 3, 3, 0));

        // Test #3 (5x5)
        for (int i = 0; i < 5; i++) {
            gameManager5x5.markSpace(Figure.CROSS, 4 - i, i);
        }
        assertEquals(gameManager5x5.getWinnerInfo(), new WinnerInfo(Row.UPWARDS_DIAGONAL, Figure.CROSS, 0, 4, 4, 0));
    }

    @Test
    public void testWinnerNotFound() {
        // Test (3x3)
        gameManager3x3.markSpace(Figure.CROSS, 0, 0);
        gameManager3x3.markSpace(Figure.ZERO, 0, 1);
        gameManager3x3.markSpace(Figure.CROSS, 0, 2);
        assertNull(gameManager3x3.getWinnerInfo());

        // Test (4x4)
        gameManager4x4.markSpace(Figure.CROSS, 2, 2);
        gameManager4x4.markSpace(Figure.CROSS, 3, 3);
        assertNull(gameManager4x4.getWinnerInfo());

        // Test (5x5)
        assertNull(gameManager5x5.getWinnerInfo());
    }
}