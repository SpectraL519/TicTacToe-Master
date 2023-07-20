package spectral.tictactoe_master.logic.utils

import android.graphics.Color


data class Status
constructor(
    val result: Result = Result.NONE,
    val player: Figure = Figure.EMPTY,
    val coordinates: List<Coordinates> = emptyList()
) {
    enum class Result {
        WIN {
            override fun inverse(): Result = LOSS
            override fun color(): Int = Color.GREEN
        },
        LOSS  {
            override fun inverse(): Result = WIN
            override fun color(): Int = Color.RED
        },
        TIE {
            override fun inverse(): Result = this
            override fun color(): Int = Color.LTGRAY
        },
        NONE {
            override fun inverse(): Result = this
            override fun color(): Int = Color.LTGRAY
        };

        abstract fun inverse(): Result
        abstract fun color(): Int
    }

    companion object {
        val NONE = Status()
        val TIE = Status(result = Result.TIE)
    }
}