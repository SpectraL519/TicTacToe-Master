package spectral.tictactoe_master.logic.game

// TODO: import spectral.tictactoe_master.activity.GameActivity
import spectral.tictactoe_master.logic.utils.*
import spectral.tictactoe_master.logic.win_condition.ClassicWinCondition
import spectral.tictactoe_master.logic.win_condition.IWinCondition


class PointGame
constructor(
    // TODO: private val context: GameActivity,
    winCondition: IWinCondition = ClassicWinCondition,
    boardSize: Int = 3,
    points: Int = 3
) : IGame {

    private val _winCondition: IWinCondition = winCondition
    private val _boardSize: Int = this._winCondition.boardSize ?: boardSize
    private val _points: Int = points.coerceAtLeast(3)

    private var _state: GameState = GameState(GameBoard(this._boardSize))
    private var _currentStatus: Status = Status.NONE

    override val state: GameState
        get() = this._state

    override val winCondition: IWinCondition
        get() = this._winCondition

    override val nextPointActionString: String
        get() = if (this._state.gameFinished) "PLAY AGAIN" else "CONTINUE"


    override fun placeFigure (x: Int, y: Int): Boolean {
        if (this._state.gameFinished)
            return false

        if (this._state.gameBlocked)
            return false

        val board = this._state.board
        if (board[x][y] == Figure.EMPTY) {
            board[x][y] = this._state.currentPlayer

            this._currentStatus = this.checkStatus()
            val pointGained = (this._currentStatus.result != Status.Result.NONE)
            val score: MutableMap<Figure, Int> = this._state.score
            if (pointGained) {
                if (this._currentStatus.result != Status.Result.LOSS)
                    score[this._currentStatus.player] = score.getOrDefault(this._currentStatus.player, -1) + 1
                else
                    score[this._currentStatus.player.next()] = score.getOrDefault(this._currentStatus.player.next(), -1) + 1
            }

            val finished: Boolean = score[Figure.O] == this._points || score[Figure.X] == this._points

            this._state.update(
                board = board,
                currentPlayer = this._state.currentPlayer.next(),
                blocked = (pointGained || this._currentStatus.result == Status.Result.TIE),
                finished = finished,
                score = score,
            )

            // TODO: if (finished)
            //     this.context.showWinMessage(this._currentStatus.result)
            return true
        }

        return false
    }

    override fun checkStatus(): Status {
        return this._winCondition.check(this._state.board)
    }

    override fun nextPointAction(): List<Coordinates>? {
        if (this._state.gameFinished) {
            this.reset()
            return null
        }

        var coordinates: List<Coordinates>? = null
        if (this._currentStatus.result != Status.Result.NONE) {
            val board = this._state.board
            if (this._currentStatus.result == Status.Result.TIE) {
                board.clear()
            }
            else {
                coordinates = this._currentStatus.coordinates
                for (c in this._currentStatus.coordinates)
                    board[c.row][c.column] = Figure.EMPTY
            }

            this._state.update(
                board = board,
                blocked = false
            )
        }

        this._currentStatus = Status.NONE
        return coordinates
    }

    override fun reset() {
        val board = this._state.board
        board.clear()

        this._state.update(
            board = board,
            currentPlayer = Figure.O,
            blocked = false,
            finished = false,
            score = GameState.DEFAULT_SCORE
        )
    }
}