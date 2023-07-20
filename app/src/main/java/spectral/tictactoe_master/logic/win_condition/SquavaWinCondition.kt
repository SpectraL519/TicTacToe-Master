package spectral.tictactoe_master.logic.win_condition

import spectral.tictactoe_master.logic.utils.Coordinates
import spectral.tictactoe_master.logic.utils.Figure
import spectral.tictactoe_master.logic.utils.GameBoard
import spectral.tictactoe_master.logic.utils.Status
import kotlin.math.pow


object SquavaWinCondition : IWinCondition {
    /*
    Board - 5x5
    The goal of the game is to place four stones in a row without first making three in a row
    */

    override val boardSize: Int = 5

    override fun check(board: GameBoard): Status {
        val winStatusO = this.checkWin(board, Figure.O)
        if (winStatusO != null)
            return winStatusO

        val winStatusX = this.checkWin(board, Figure.X)
        if (winStatusX != null)
            return winStatusX

        val lossStatusO = this.checkLoss(board, Figure.O)
        if (lossStatusO != null)
            return lossStatusO

        val lossStatusX = this.checkLoss(board, Figure.X)
        if (lossStatusX != null)
            return lossStatusX

        if (board.full())
            return Status.TIE
        return Status.NONE
    }

    private val maxEval: Long = 10.0.pow(this.boardSize.toDouble()).toLong()
    override fun evaluation(board: GameBoard, player: Figure): Long {
        val opponent = player.next()

        var playerPairs = 0
        var playerTriples = 0
        var opponentPairs = 0
        var opponentTriples = 0
        var evaluation: Long = 0

        // win combinations
        for (win: List<Coordinates> in this.WIN) {
            var playerCount = 0
            var opponentCount = 0

            for (c in win) {
                if (board[c.row][c.column] == player)
                    playerCount++
                else if (board[c.row][c.column] == opponent)
                    opponentCount++
            }

            if (playerCount == 4)
                return this.maxEval
            if (opponentCount == 4)
                return -this.maxEval

            if (playerCount * opponentCount == 0) {
                evaluation += (playerCount - opponentCount) * 5 + 1

                if (playerCount == 3)
                    playerTriples++
                else if (playerCount == 2)
                    playerPairs++
                else if (opponentCount == 3)
                    opponentTriples++
                else if (opponentCount == 2)
                    opponentPairs++
            }
        }

        if (playerTriples * opponentTriples == 0)
            evaluation += (playerTriples - opponentTriples) * 100
        if (playerPairs * opponentPairs == 0)
            evaluation += (playerPairs - opponentPairs) * 25

        // loss combinations
        for (loss: List<Coordinates> in this.LOSS) {
            var playerCount = 0
            var opponentCount = 0

            for (c in loss) {
                if (board[c.row][c.column] == player)
                    playerCount++
                else if (board[c.row][c.column] == opponent)
                    opponentCount++
            }

            // loss
            if (playerCount == 3)
                return -this.maxEval
            if (opponentCount == 3)
                return this.maxEval

            if (playerCount * opponentCount == 0)
                evaluation -= (playerCount - opponentCount) * 3 + 1
        }

        return evaluation
    }



    private fun checkWin(board: GameBoard, player: Figure): Status? {
        for (win: List<Coordinates> in this.WIN) {
            if (
                board[win[0].row][win[0].column] == player &&
                board[win[1].row][win[1].column] == player &&
                board[win[2].row][win[2].column] == player &&
                board[win[3].row][win[3].column] == player
            ) {
                return Status(
                    result = Status.Result.WIN,
                    player = player,
                    coordinates = win
                )
            }
        }

        return null
    }

    private fun checkLoss(board: GameBoard, player: Figure): Status? {
        for (loss: List<Coordinates> in this.LOSS) {
            if (
                board[loss[0].row][loss[0].column] == player &&
                board[loss[1].row][loss[1].column] == player &&
                board[loss[2].row][loss[2].column] == player
            ) {
                return Status(
                    result = Status.Result.LOSS,
                    player = player,
                    coordinates = loss
                )
            }
        }

        return null
    }

    private val WIN: List<List<Coordinates>> = listOf(
        listOf(Coordinates(0, 0), Coordinates(0, 1), Coordinates(0, 2), Coordinates(0, 3)),
        listOf(Coordinates(1, 0), Coordinates(1, 1), Coordinates(1, 2), Coordinates(1, 3)),
        listOf(Coordinates(2, 0), Coordinates(2, 1), Coordinates(2, 2), Coordinates(2, 3)),
        listOf(Coordinates(3, 0), Coordinates(3, 1), Coordinates(3, 2), Coordinates(3, 3)),
        listOf(Coordinates(4, 0), Coordinates(4, 1), Coordinates(4, 2), Coordinates(4, 3)),
        listOf(Coordinates(0, 1), Coordinates(0, 2), Coordinates(0, 3), Coordinates(0, 4)),
        listOf(Coordinates(1, 1), Coordinates(1, 2), Coordinates(1, 3), Coordinates(1, 4)),
        listOf(Coordinates(2, 1), Coordinates(2, 2), Coordinates(2, 3), Coordinates(2, 4)),
        listOf(Coordinates(3, 1), Coordinates(3, 2), Coordinates(3, 3), Coordinates(3, 4)),
        listOf(Coordinates(4, 1), Coordinates(4, 2), Coordinates(4, 3), Coordinates(4, 4)),
        listOf(Coordinates(0, 0), Coordinates(1, 0), Coordinates(2, 0), Coordinates(3, 0)),
        listOf(Coordinates(0, 1), Coordinates(1, 1), Coordinates(2, 1), Coordinates(3, 1)),
        listOf(Coordinates(0, 2), Coordinates(1, 2), Coordinates(2, 2), Coordinates(3, 2)),
        listOf(Coordinates(0, 3), Coordinates(1, 3), Coordinates(2, 3), Coordinates(3, 3)),
        listOf(Coordinates(0, 4), Coordinates(1, 4), Coordinates(2, 4), Coordinates(3, 4)),
        listOf(Coordinates(1, 0), Coordinates(2, 0), Coordinates(3, 0), Coordinates(4, 0)),
        listOf(Coordinates(1, 1), Coordinates(2, 1), Coordinates(3, 1), Coordinates(4, 1)),
        listOf(Coordinates(1, 2), Coordinates(2, 2), Coordinates(3, 2), Coordinates(4, 2)),
        listOf(Coordinates(1, 3), Coordinates(2, 3), Coordinates(3, 3), Coordinates(4, 3)),
        listOf(Coordinates(1, 4), Coordinates(2, 4), Coordinates(3, 4), Coordinates(4, 4)),
        listOf(Coordinates(0, 1), Coordinates(1, 2), Coordinates(2, 3), Coordinates(3, 4)),
        listOf(Coordinates(0, 0), Coordinates(1, 1), Coordinates(2, 2), Coordinates(3, 3)),
        listOf(Coordinates(1, 1), Coordinates(2, 2), Coordinates(3, 3), Coordinates(4, 4)),
        listOf(Coordinates(1, 0), Coordinates(2, 1), Coordinates(3, 2), Coordinates(4, 3)),
        listOf(Coordinates(0, 3), Coordinates(1, 2), Coordinates(2, 1), Coordinates(3, 0)),
        listOf(Coordinates(0, 4), Coordinates(1, 3), Coordinates(2, 2), Coordinates(3, 1)),
        listOf(Coordinates(1, 3), Coordinates(2, 2), Coordinates(3, 1), Coordinates(4, 0)),
        listOf(Coordinates(1, 4), Coordinates(2, 3), Coordinates(3, 2), Coordinates(4, 1))
    )

    private val LOSS: List<List<Coordinates>> = listOf(
        listOf(Coordinates(0, 0), Coordinates(0, 1), Coordinates(0, 2)),
        listOf(Coordinates(0, 1), Coordinates(0, 2), Coordinates(0, 3)),
        listOf(Coordinates(0, 2), Coordinates(0, 3), Coordinates(0, 4)),
        listOf(Coordinates(1, 0), Coordinates(1, 1), Coordinates(1, 2)),
        listOf(Coordinates(1, 1), Coordinates(1, 2), Coordinates(1, 3)),
        listOf(Coordinates(1, 2), Coordinates(1, 3), Coordinates(1, 4)),
        listOf(Coordinates(2, 0), Coordinates(2, 1), Coordinates(2, 2)),
        listOf(Coordinates(2, 1), Coordinates(2, 2), Coordinates(2, 3)),
        listOf(Coordinates(2, 2), Coordinates(2, 3), Coordinates(2, 4)),
        listOf(Coordinates(3, 0), Coordinates(3, 1), Coordinates(3, 2)),
        listOf(Coordinates(3, 1), Coordinates(3, 2), Coordinates(3, 3)),
        listOf(Coordinates(3, 2), Coordinates(3, 3), Coordinates(3, 4)),
        listOf(Coordinates(4, 0), Coordinates(4, 1), Coordinates(4, 2)),
        listOf(Coordinates(4, 1), Coordinates(4, 2), Coordinates(4, 3)),
        listOf(Coordinates(4, 2), Coordinates(4, 3), Coordinates(4, 4)),
        listOf(Coordinates(0, 0), Coordinates(1, 0), Coordinates(2, 0)),
        listOf(Coordinates(1, 0), Coordinates(2, 0), Coordinates(3, 0)),
        listOf(Coordinates(2, 0), Coordinates(3, 0), Coordinates(4, 0)),
        listOf(Coordinates(0, 1), Coordinates(1, 1), Coordinates(2, 1)),
        listOf(Coordinates(1, 1), Coordinates(2, 1), Coordinates(3, 1)),
        listOf(Coordinates(2, 1), Coordinates(3, 1), Coordinates(4, 1)),
        listOf(Coordinates(0, 2), Coordinates(1, 2), Coordinates(2, 2)),
        listOf(Coordinates(1, 2), Coordinates(2, 2), Coordinates(3, 2)),
        listOf(Coordinates(2, 2), Coordinates(3, 2), Coordinates(4, 2)),
        listOf(Coordinates(0, 3), Coordinates(1, 3), Coordinates(2, 3)),
        listOf(Coordinates(1, 3), Coordinates(2, 3), Coordinates(3, 3)),
        listOf(Coordinates(2, 3), Coordinates(3, 3), Coordinates(4, 3)),
        listOf(Coordinates(0, 4), Coordinates(1, 4), Coordinates(2, 4)),
        listOf(Coordinates(1, 4), Coordinates(2, 4), Coordinates(3, 4)),
        listOf(Coordinates(2, 4), Coordinates(3, 4), Coordinates(4, 4)),
        listOf(Coordinates(0, 2), Coordinates(1, 3), Coordinates(2, 4)),
        listOf(Coordinates(0, 1), Coordinates(1, 2), Coordinates(2, 3)),
        listOf(Coordinates(1, 2), Coordinates(2, 3), Coordinates(3, 4)),
        listOf(Coordinates(0, 0), Coordinates(1, 1), Coordinates(2, 2)),
        listOf(Coordinates(1, 1), Coordinates(2, 2), Coordinates(3, 3)),
        listOf(Coordinates(2, 2), Coordinates(3, 3), Coordinates(4, 4)),
        listOf(Coordinates(1, 0), Coordinates(2, 1), Coordinates(3, 2)),
        listOf(Coordinates(2, 1), Coordinates(3, 2), Coordinates(4, 3)),
        listOf(Coordinates(2, 0), Coordinates(3, 1), Coordinates(4, 2)),
        listOf(Coordinates(0, 2), Coordinates(1, 1), Coordinates(2, 0)),
        listOf(Coordinates(0, 3), Coordinates(1, 2), Coordinates(2, 1)),
        listOf(Coordinates(1, 2), Coordinates(2, 1), Coordinates(3, 0)),
        listOf(Coordinates(0, 4), Coordinates(1, 3), Coordinates(2, 2)),
        listOf(Coordinates(1, 3), Coordinates(2, 2), Coordinates(3, 1)),
        listOf(Coordinates(2, 2), Coordinates(3, 1), Coordinates(4, 0)),
        listOf(Coordinates(1, 4), Coordinates(2, 3), Coordinates(3, 2)),
        listOf(Coordinates(2, 3), Coordinates(3, 2), Coordinates(4, 1)),
        listOf(Coordinates(2, 4), Coordinates(3, 3), Coordinates(4, 2))
    )
}