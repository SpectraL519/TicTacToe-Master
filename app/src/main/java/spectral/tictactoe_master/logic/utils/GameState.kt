package spectral.tictactoe_master.logic.utils


data class GameState
constructor(
    private var _board: GameBoard,
    private var _currentPlayer: Figure = Figure.O,
    private var _blocked: Boolean = false,
    private var _finished: Boolean = false,
    private var _score: MutableMap<Figure, Int> = DEFAULT_SCORE,
) {
    val board get() = this._board
    val boardSize get() = this._board.size()
    fun figure (x: Int, y: Int): Figure = this._board[x][y]

    val currentPlayer get() = this._currentPlayer
    val gameBlocked get() = this._blocked
    val gameFinished get() = this._finished

    val score get() = this._score
    fun getScore (player: Figure): Int =
        this._score.getOrDefault(player, 0)

    fun update (
        board: GameBoard = this._board,
        currentPlayer: Figure = this._currentPlayer,
        blocked: Boolean = this._blocked,
        finished: Boolean = this._finished,
        score: MutableMap<Figure, Int> = this._score,
    ) {
        this._board = board
        this._currentPlayer = currentPlayer
        this._blocked = blocked
        this._finished = finished
        this._score = score
    }

    // TODO: modify values in the _score map without replacing the instance

    companion object {
        val DEFAULT_SCORE: MutableMap<Figure, Int>
            get() = mutableMapOf(
                Figure.O to 0,
                Figure.X to 0,
                Figure.EMPTY to 0
            )
    }
}