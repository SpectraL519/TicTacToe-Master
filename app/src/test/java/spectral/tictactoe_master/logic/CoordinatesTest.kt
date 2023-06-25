package spectral.tictactoe_master.logic

import spectral.tictactoe_master.logic.utils.Coordinates
import org.junit.Assert
import org.junit.Test



class CoordinatesTest {
    @Test
    fun regexTest() {
        val c = Coordinates(1, 2)
        val cStr = c.toString()
        println(cStr)

        val cTest = Coordinates.fromString(cStr)
        println(cTest.toString())
        Assert.assertEquals(c, cTest)
    }
}