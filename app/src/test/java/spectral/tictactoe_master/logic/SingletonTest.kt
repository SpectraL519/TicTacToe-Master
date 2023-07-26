package spectral.tictactoe_master.logic

import org.junit.Assert
import org.junit.Test
import spectral.tictactoe_master.logic.win_condition.ClassicWinCondition
import spectral.tictactoe_master.logic.win_condition.MobiusStripWinCondition



class SingletonTest {
    @Test
    fun classicWinConditionTest() {
        val condition1 = ClassicWinCondition
        val condition2 = ClassicWinCondition

        Assert.assertSame(condition1, condition2)
    }

    @Test
    fun mobiusStripWinConditionTest() {
        val condition1 = MobiusStripWinCondition
        val condition2 = MobiusStripWinCondition

        Assert.assertSame(condition1, condition2)
    }
}