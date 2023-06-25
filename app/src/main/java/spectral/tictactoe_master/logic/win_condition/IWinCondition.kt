package spectral.tictactoe_master.logic.win_condition

import spectral.tictactoe_master.logic.utils.Figure
import spectral.tictactoe_master.logic.utils.GameBoard
import spectral.tictactoe_master.logic.utils.Status



interface IWinCondition {
    fun check (board: GameBoard) : Status
    fun getEvaluation (board: GameBoard, player: Figure): Long

    enum class Result {
        NONE, TIE, X, O
    }
}