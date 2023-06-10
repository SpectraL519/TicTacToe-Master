package spectral.tictactoe_master.logic.utils



data class GameBoard
constructor(private val _size: Int) {

    private var _board: Array<Array<Figure>> =
        Array(this._size) { Array(this._size) { Figure.EMPTY } }

    fun size(): Int = this._size

    fun full(): Boolean = this._board.all { row -> row.all { it != Figure.EMPTY } }
    fun empty(): Boolean = this._board.all { row -> row.all { it == Figure.EMPTY } }

    operator fun get (index: Int): Array<Figure> = this._board[index]
    operator fun get (coordinates: Coordinates): Figure =
        this._board[coordinates.row][coordinates.column]

    fun row (index: Int): Array<Figure> = this._board[index]

    fun column( index: Int): Array<Figure> =
        Array(this._size) { this._board[it][index] }

    fun diagonal (right: Boolean = true): Array<Figure> {
        if (right)
            return Array(this._size) { this._board[it][it] }
        return Array(this._size) { this._board[it][this._size - 1 - it] }
    }

    fun clear() {
        this._board.forEach { row -> row.fill(Figure.EMPTY) }
    }
}