package spectral.tictactoe_master.logic.win_condition

import spectral.tictactoe_master.logic.utils.Figure
import spectral.tictactoe_master.logic.utils.GameBoard
import spectral.tictactoe_master.logic.utils.Status



object ToeTacTicWinCondition : IWinCondition {
    override val boardSize: Int? = null

    override fun check(board: GameBoard): Status {
        val classicStatus: Status = ClassicWinCondition.check(board)
        return Status(
            result = classicStatus.result.inverse(),
            player = classicStatus.player,
            coordinates = classicStatus.coordinates
        )
    }

    override fun evaluation(board: GameBoard, player: Figure): Long {
        // TODO: check functionality
        return -ClassicWinCondition.evaluation(board, player)
    }
}