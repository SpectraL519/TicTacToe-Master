package spectral.tictactoe_master.logic.game

import spectral.tictactoe_master.logic.utils.Coordinates
import spectral.tictactoe_master.logic.utils.GameState
import spectral.tictactoe_master.logic.utils.Status
import spectral.tictactoe_master.logic.win_condition.IWinCondition


interface IGame {
    val state: GameState
    val winCondition: IWinCondition
    val nextPointActionString: String

    fun placeFigure (x: Int, y: Int) : Boolean
    fun checkStatus() : Status
    fun nextPointAction() : List<Coordinates>?
    fun reset()
}