package spectral.tictactoe_master.logic.win_condition

import spectral.tictactoe_master.logic.utils.Figure
import spectral.tictactoe_master.logic.utils.GameBoard
import spectral.tictactoe_master.logic.utils.Status



interface IWinCondition {
    fun check (board: GameBoard) : Status
    fun evaluation (board: GameBoard, player: Figure): Long

    enum class Result {
        NONE { override fun inverse(): Result = this },
        TIE { override fun inverse(): Result = this },
        X { override fun inverse(): Result = O },
        O { override fun inverse(): Result = X };

        abstract fun inverse(): Result
    }
}