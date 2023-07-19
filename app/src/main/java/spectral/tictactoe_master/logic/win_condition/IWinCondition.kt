package spectral.tictactoe_master.logic.win_condition

import spectral.tictactoe_master.logic.utils.Figure
import spectral.tictactoe_master.logic.utils.GameBoard
import spectral.tictactoe_master.logic.utils.Status



interface IWinCondition {
    val boardSize: Int? // fixed board size if not null
    fun check (board: GameBoard) : Status
    fun evaluation (board: GameBoard, player: Figure): Long

    enum class Result {
        NONE { override fun inverse(): Result = this },
        TIE { override fun inverse(): Result = this },
        X { override fun inverse(): Result = O },
        O { override fun inverse(): Result = X };

        abstract fun inverse(): Result
    }

    companion object {
        fun getResult(player: Figure) =
            when (player) {
                Figure.O -> Result.O
                Figure.X -> Result.X
                else -> Result.NONE
            }
    }
}