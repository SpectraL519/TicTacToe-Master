package spectral.tictactoe_master.logic.win_condition

import spectral.tictactoe_master.logic.utils.Figure
import spectral.tictactoe_master.logic.utils.GameBoard
import spectral.tictactoe_master.logic.utils.Status



interface IWinCondition {
    val boardSize: Int? // fixed board size if not null
    fun check (board: GameBoard) : Status
    fun evaluation (board: GameBoard, player: Figure): Long
}