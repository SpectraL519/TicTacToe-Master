package spectral.tictactoe_master.logic

import org.junit.Assert
import org.junit.Test
import spectral.tictactoe_master.logic.utils.Coordinates
import spectral.tictactoe_master.logic.utils.Figure
import spectral.tictactoe_master.logic.utils.GameBoard
import spectral.tictactoe_master.logic.utils.Status
import spectral.tictactoe_master.logic.win_condition.*


class WinConditionTest {
    @Test
    fun classicWinConditionTest() {
        val winCondition: IWinCondition = ClassicWinCondition

        val size = 3
        val board = GameBoard(size)

        var status = winCondition.check(board)
        Assert.assertEquals(Status.Result.NONE, status.result)
        Assert.assertEquals(Figure.EMPTY, status.player)
        Assert.assertEquals(emptyList<Coordinates>(), status.coordinates)

        for (i in 0 until size) {
            board.clear()
            board.row(i).fill(Figure.O)
            status = winCondition.check(board)
            Assert.assertEquals(Status.Result.WIN, status.result)
            Assert.assertEquals(Figure.O, status.player)
            Assert.assertEquals(
                List(size) { index -> Coordinates(i, index) },
                status.coordinates
            )

            board.clear()
            board.row(i).fill(Figure.X)
            status = winCondition.check(board)
            Assert.assertEquals(Status.Result.WIN, status.result)
            Assert.assertEquals(Figure.X, status.player)
            Assert.assertEquals(
                List(size) { index -> Coordinates(i, index) },
                status.coordinates
            )

            board.clear()
            for (j in 0 until size)
                board[j][i] = Figure.O
            status = winCondition.check(board)
            Assert.assertEquals(Status.Result.WIN, status.result)
            Assert.assertEquals(Figure.O, status.player)
            Assert.assertEquals(
                List(size) { index -> Coordinates(index, i) },
                status.coordinates
            )

            board.clear()
            for (j in 0 until size)
                board[j][i] = Figure.X
            status = winCondition.check(board)
            Assert.assertEquals(Status.Result.WIN, status.result)
            Assert.assertEquals(Figure.X, status.player)
            Assert.assertEquals(
                List(size) { index -> Coordinates(index, i) },
                status.coordinates
            )
        }

        board.clear()
        for (i in 0 until size)
            board[i][i] = Figure.O
        status = winCondition.check(board)
        Assert.assertEquals(Status.Result.WIN, status.result)
        Assert.assertEquals(Figure.O, status.player)
        Assert.assertEquals(
            List(size) { index -> Coordinates(index, index) },
            status.coordinates
        )

        board.clear()
        for (i in 0 until size)
            board[i][i] = Figure.X
        status = winCondition.check(board)
        Assert.assertEquals(Status.Result.WIN, status.result)
        Assert.assertEquals(Figure.X, status.player)
        Assert.assertEquals(
            List(size) { index -> Coordinates(index, index) },
            status.coordinates
        )

        board.clear()
        for (i in 0 until size)
            board[i][size - 1 - i] = Figure.O
        status = winCondition.check(board)
        Assert.assertEquals(Status.Result.WIN, status.result)
        Assert.assertEquals(Figure.O, status.player)
        Assert.assertEquals(
            List(size) { index -> Coordinates(index, size - 1 - index) },
            status.coordinates
        )

        board.clear()
        for (i in 0 until size)
            board[i][size - 1 - i] = Figure.X
        status = winCondition.check(board)
        Assert.assertEquals(Status.Result.WIN, status.result)
        Assert.assertEquals(Figure.X, status.player)
        Assert.assertEquals(
            List(size) { index -> Coordinates(index, size - 1 - index) },
            status.coordinates
        )

        board.clear()
        board[0][0] = Figure.O
        board[0][1] = Figure.O
        board[0][2] = Figure.X
        board[1][0] = Figure.X
        board[1][1] = Figure.X
        board[1][2] = Figure.O
        board[2][0] = Figure.O
        board[2][1] = Figure.X
        board[2][2] = Figure.O
        status = winCondition.check(board)
        Assert.assertEquals(Status.Result.TIE, status.result)
        Assert.assertEquals(Figure.EMPTY, status.player)
        Assert.assertEquals(emptyList<Coordinates>(), status.coordinates)
    }


    @Test
    fun mobiusStripWinConditionTest() {
        val winCondition: IWinCondition = MobiusStripWinCondition

        val size = 5
        val board = GameBoard(size)

        var status = winCondition.check(board)
        Assert.assertEquals(Status.Result.NONE, status.result)
        Assert.assertEquals(Figure.EMPTY, status.player)
        Assert.assertEquals(emptyList<Coordinates>(), status.coordinates)

        // Classic wins
        for (i in 0 until size) {
            board.clear()
            board.row(i).fill(Figure.O)
            status = winCondition.check(board)
            Assert.assertEquals(Status.Result.WIN, status.result)
            Assert.assertEquals(Figure.O, status.player)
            Assert.assertEquals(
                List(size) { index -> Coordinates(i, index) },
                status.coordinates
            )

            board.clear()
            board.row(i).fill(Figure.X)
            status = winCondition.check(board)
            Assert.assertEquals(Status.Result.WIN, status.result)
            Assert.assertEquals(Figure.X, status.player)
            Assert.assertEquals(
                List(size) { index -> Coordinates(i, index) },
                status.coordinates
            )

            board.clear()
            for (j in 0 until size)
                board[j][i] = Figure.O
            status = winCondition.check(board)
            Assert.assertEquals(Status.Result.WIN, status.result)
            Assert.assertEquals(Figure.O, status.player)
            Assert.assertEquals(
                List(size) { index -> Coordinates(index, i) },
                status.coordinates
            )

            board.clear()
            for (j in 0 until size)
                board[j][i] = Figure.X
            status = winCondition.check(board)
            Assert.assertEquals(Status.Result.WIN, status.result)
            Assert.assertEquals(Figure.X, status.player)
            Assert.assertEquals(
                List(size) { index -> Coordinates(index, i) },
                status.coordinates
            )
        }

        board.clear()
        for (i in 0 until size)
            board[i][i] = Figure.O
        status = winCondition.check(board)
        Assert.assertEquals(Status.Result.WIN, status.result)
        Assert.assertEquals(Figure.O, status.player)
        Assert.assertEquals(
            List(size) { index -> Coordinates(index, index) },
            status.coordinates
        )

        board.clear()
        for (i in 0 until size)
            board[i][i] = Figure.X
        status = winCondition.check(board)
        Assert.assertEquals(Status.Result.WIN, status.result)
        Assert.assertEquals(Figure.X, status.player)
        Assert.assertEquals(
            List(size) { index -> Coordinates(index, index) },
            status.coordinates
        )

        board.clear()
        for (i in 0 until size)
            board[i][size - 1 - i] = Figure.O
        status = winCondition.check(board)
        Assert.assertEquals(Status.Result.WIN, status.result)
        Assert.assertEquals(Figure.O, status.player)
        Assert.assertEquals(
            List(size) { index -> Coordinates(index, size - 1 - index) },
            status.coordinates
        )

        board.clear()
        for (i in 0 until size)
            board[i][size - 1 - i] = Figure.X
        status = winCondition.check(board)
        Assert.assertEquals(Status.Result.WIN, status.result)
        Assert.assertEquals(Figure.X, status.player)
        Assert.assertEquals(
            List(size) { index -> Coordinates(index, size - 1 - index) },
            status.coordinates
        )

        board.clear()
        var currentFigure: Figure = Figure.O
        for (i: Int in 0 until size) {
            for (j: Int in 0 until size) {
                board[i][j] = currentFigure
                currentFigure = currentFigure.next()
            }
        }
        board[2][2] = Figure.X
        status = winCondition.check(board)
        Assert.assertEquals(Status.Result.TIE, status.result)
        Assert.assertEquals(Figure.EMPTY, status.player)
        Assert.assertEquals(emptyList<Coordinates>(), status.coordinates)

        // Mobius strip wins
        board.clear()
        var coordinates: List<Coordinates>
        for (row: Int in 0 until size) {
            for (lastConsecutive: Int in 0 until size - 1) {
                val coordinatesA = List(lastConsecutive + 1) {
                        index -> Coordinates(row, index)
                }
                val coordinatesB = List(size - 1 - lastConsecutive) {
                        index -> Coordinates(size - 1 - row, size - 1 - index)
                }
                coordinates = (coordinatesA + coordinatesB).sorted()

                board.clear()
                for (column: Int in 0..lastConsecutive)
                    board[row][column] = Figure.O
                for (column: Int in lastConsecutive + 1 until size)
                    board[size - 1 - row][column] = Figure.O

                println("\nboard O:")
                for (i: Int in 0 until size) {
                    for (j: Int in 0 until size) {
                        print(board[i][j].toString() + " ")
                    }
                    println()
                }

                status = winCondition.check(board)
                println("result: " + status.result.toString())
                for (c in status.coordinates)
                    print("$c ")
                println()
                Assert.assertEquals(Status.Result.WIN, status.result)
                Assert.assertEquals(Figure.O, status.player)
                Assert.assertEquals(coordinates, status.coordinates)

                board.clear()
                for (column: Int in 0..lastConsecutive)
                    board[row][column] = Figure.X
                for (column: Int in lastConsecutive + 1 until size)
                    board[size - 1 - row][column] = Figure.X

                println("\nboard X:")
                for (i: Int in 0 until size) {
                    for (j: Int in 0 until size) {
                        print(board[i][j].toString() + " ")
                    }
                    println()
                }

                status = winCondition.check(board)
                println("result: " + status.result.toString())
                for (c in status.coordinates)
                    print("$c ")
                println()
                Assert.assertEquals(Status.Result.WIN, status.result)
                Assert.assertEquals(Figure.X, status.player)
                Assert.assertEquals(coordinates, status.coordinates)

                println("\n")
            }
        }
    }


    @Test
    fun toeTacTicWinConditionTest() {
        val winCondition: IWinCondition = ToeTacTicWinCondition

        val size = 3
        val board = GameBoard(size)

        var status = winCondition.check(board)
        Assert.assertEquals(Status.Result.NONE, status.result)
        Assert.assertEquals(Figure.EMPTY, status.player)
        Assert.assertEquals(emptyList<Coordinates>(), status.coordinates)

        for (i in 0 until size) {
            board.clear()
            board.row(i).fill(Figure.O)
            status = winCondition.check(board)
            Assert.assertEquals(Status.Result.LOSS, status.result)
            Assert.assertEquals(Figure.O, status.player)
            Assert.assertEquals(
                List(size) { index -> Coordinates(i, index) },
                status.coordinates
            )

            board.clear()
            board.row(i).fill(Figure.X)
            status = winCondition.check(board)
            Assert.assertEquals(Status.Result.LOSS, status.result)
            Assert.assertEquals(Figure.X, status.player)
            Assert.assertEquals(
                List(size) { index -> Coordinates(i, index) },
                status.coordinates
            )

            board.clear()
            for (j in 0 until size)
                board[j][i] = Figure.O
            status = winCondition.check(board)
            Assert.assertEquals(Status.Result.LOSS, status.result)
            Assert.assertEquals(Figure.O, status.player)
            Assert.assertEquals(
                List(size) { index -> Coordinates(index, i) },
                status.coordinates
            )

            board.clear()
            for (j in 0 until size)
                board[j][i] = Figure.X
            status = winCondition.check(board)
            Assert.assertEquals(Status.Result.LOSS, status.result)
            Assert.assertEquals(Figure.X, status.player)
            Assert.assertEquals(
                List(size) { index -> Coordinates(index, i) },
                status.coordinates
            )
        }

        board.clear()
        for (i in 0 until size)
            board[i][i] = Figure.O
        status = winCondition.check(board)
        Assert.assertEquals(Status.Result.LOSS, status.result)
        Assert.assertEquals(Figure.O, status.player)
        Assert.assertEquals(
            List(size) { index -> Coordinates(index, index) },
            status.coordinates
        )

        board.clear()
        for (i in 0 until size)
            board[i][i] = Figure.X
        status = winCondition.check(board)
        Assert.assertEquals(Status.Result.LOSS, status.result)
        Assert.assertEquals(Figure.X, status.player)
        Assert.assertEquals(
            List(size) { index -> Coordinates(index, index) },
            status.coordinates
        )

        board.clear()
        for (i in 0 until size)
            board[i][size - 1 - i] = Figure.O
        status = winCondition.check(board)
        Assert.assertEquals(Status.Result.LOSS, status.result)
        Assert.assertEquals(Figure.O, status.player)
        Assert.assertEquals(
            List(size) { index -> Coordinates(index, size - 1 - index) },
            status.coordinates
        )

        board.clear()
        for (i in 0 until size)
            board[i][size - 1 - i] = Figure.X
        status = winCondition.check(board)
        Assert.assertEquals(Status.Result.LOSS, status.result)
        Assert.assertEquals(Figure.X, status.player)
        Assert.assertEquals(
            List(size) { index -> Coordinates(index, size - 1 - index) },
            status.coordinates
        )

        board.clear()
        board[0][0] = Figure.O
        board[0][1] = Figure.O
        board[0][2] = Figure.X
        board[1][0] = Figure.X
        board[1][1] = Figure.X
        board[1][2] = Figure.O
        board[2][0] = Figure.O
        board[2][1] = Figure.X
        board[2][2] = Figure.O
        status = winCondition.check(board)
        Assert.assertEquals(Status.Result.TIE, status.result)
        Assert.assertEquals(Figure.EMPTY, status.player)
        Assert.assertEquals(emptyList<Coordinates>(), status.coordinates)
    }


    @Test
    fun squavaWinConditionTest() {
        val winCondition = SquavaWinCondition
        val board = GameBoard(SquavaWinCondition.boardSize)

        var status = winCondition.check(board)
        Assert.assertEquals(Status.Result.NONE, status.result)
        Assert.assertEquals(emptyList<Coordinates>(), status.coordinates)

        // check win situations
        val WIN = SquavaWinCondition::class.java
            .getDeclaredField("WIN")
            .apply { isAccessible = true }
            .get(winCondition) as List<List<Coordinates>>

        for (win: List<Coordinates> in WIN) {
            board[win[0].row][win[0].column] = Figure.O
            board[win[1].row][win[1].column] = Figure.O
            board[win[2].row][win[2].column] = Figure.O
            board[win[3].row][win[3].column] = Figure.O

            status = winCondition.check(board)
            Assert.assertEquals(Status.Result.WIN, status.result)
            Assert.assertEquals(Figure.O, status.player)
            Assert.assertEquals(win, status.coordinates)

            board[win[0].row][win[0].column] = Figure.X
            board[win[1].row][win[1].column] = Figure.X
            board[win[2].row][win[2].column] = Figure.X
            board[win[3].row][win[3].column] = Figure.X

            status = winCondition.check(board)
            Assert.assertEquals(Status.Result.WIN, status.result)
            Assert.assertEquals(Figure.X, status.player)
            Assert.assertEquals(win, status.coordinates)

            board.clear()
        }

        // check loss situations
        val LOSS = SquavaWinCondition::class.java
            .getDeclaredField("LOSS")
            .apply { isAccessible = true }
            .get(winCondition) as List<List<Coordinates>>

        for (loss: List<Coordinates> in LOSS) {
            board[loss[0].row][loss[0].column] = Figure.O
            board[loss[1].row][loss[1].column] = Figure.O
            board[loss[2].row][loss[2].column] = Figure.O

            status = winCondition.check(board)
            Assert.assertEquals(Status.Result.LOSS, status.result)
            Assert.assertEquals(Figure.O, status.player)
            Assert.assertEquals(loss, status.coordinates)

            board[loss[0].row][loss[0].column] = Figure.X
            board[loss[1].row][loss[1].column] = Figure.X
            board[loss[2].row][loss[2].column] = Figure.X

            status = winCondition.check(board)
            Assert.assertEquals(Status.Result.LOSS, status.result)
            Assert.assertEquals(Figure.X, status.player)
            Assert.assertEquals(loss, status.coordinates)

            board.clear()
        }

        // TODO: TIE test
    }
}