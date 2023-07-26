package spectral.tictactoe_master.logic.win_condition

import android.graphics.Color
import spectral.tictactoe_master.logic.utils.Figure
import spectral.tictactoe_master.logic.utils.GameBoard
import spectral.tictactoe_master.logic.utils.Status



interface IWinCondition {
    val boardSize: Int? // fixed board size if not null
    fun check (board: GameBoard) : Status
    fun evaluation (board: GameBoard, player: Figure): Long


    enum class Result {
        WIN {
            override fun inverse(): Result = LOSS
            override fun color(): Int = Color.GREEN

            override fun toString(): String = "WIN"
        },
        LOSS  {
            override fun inverse(): Result = WIN
            override fun color(): Int = Color.RED

            override fun toString(): String = "LOSS"
        },
        TIE {
            override fun inverse(): Result = this
            override fun color(): Int = Color.LTGRAY

            override fun toString(): String = "TIE"
        },
        NONE {
            override fun inverse(): Result = this
            override fun color(): Int = Color.LTGRAY

            override fun toString(): String = "NONE"
        };

        abstract fun inverse(): Result
        abstract fun color(): Int
    }
}