//Enums used for storing the state of a tile. Better than strings in some cases because of case BLANK
enum class TileState {
    X,
    O,
    BLANK
}

//Tile state array is primarily used to reference when a tile is interacted with
//i.e. if a tile isn't blank, its state shouldn't be changed. This array stores that information
var tileStateArray = arrayOf(TileState.BLANK, TileState.BLANK, TileState.BLANK, TileState.BLANK,
    TileState.BLANK, TileState.BLANK, TileState.BLANK, TileState.BLANK, TileState.BLANK)

//X and O arrays store the numerical index (0 thru 8) of tiles with Xs or Os respectively
//Used as a reference for win conditions (see fun checkWinCondition())
var xArray = mutableListOf<Int>()
var oArray = mutableListOf<Int>()

//Strings are used here instead of TileState because there was no need for case BLANK and strings were easier to
//integrate into the controller when translating to the view
//i.e. no need to convert TileState to a string when displaying the current player or the winner
var winner = "No winner yet"

//Game starts with X
var currentPlayer = "X"
var isGameWon = false


fun switchPlayer(){
    if (currentPlayer == "X"){
        currentPlayer = "O"
    } else if (currentPlayer == "O"){
        currentPlayer = "X"
    }
}

//List of all of the win conditions stored as a group of three tile indexes
//For example, winConditions[7] is the diagonal from top left to bottom right
val winConditions = listOf(
    listOf(0,1,2),
    listOf(3,4,5),
    listOf(6,7,8),
    listOf(0,3,6),
    listOf(1,4,7),
    listOf(2,5,8),
    listOf(0,4,8),
    listOf(2,4,6)
)

//Looks at where Xs and Os have been placed via xArray and oArray in order to see if the game has been won
//If either array contains all three Ints (tile indexes) of any of the win conditions, then the game is won
fun checkWinCondition(){
    for (winIndex in 0..7) {
        if (xArray.containsAll(winConditions[winIndex])||oArray.containsAll(winConditions[winIndex])){
            //Since checkWinCondition is to be called before the player is switched, we can reference currentPlayer
            //as the player who made the winning move
            winner = currentPlayer
            isGameWon = true

        } else if (!tileStateArray.contains(TileState.BLANK)){
            //If nobody has won and all the tiles are filled, the game is a tie
            winner = "nobody, it's a tie!"
            isGameWon = true
        }
    }
}

//Stores what (X or O) has been placed in a given tile as an index (0 thru 8) in the xArray or oArray
//Also changes the TileState of the given tile in tileStateArray so that it cannot be changed again
fun gameMove(tileIndex: Int){
    if (currentPlayer == "X") {
        xArray.add(tileIndex)
        tileStateArray[tileIndex] = TileState.X
    } else if (currentPlayer == "O") {
        oArray.add(tileIndex)
        tileStateArray[tileIndex] = TileState.O
    }
}

//Resets all the game variables to the start of the game
fun resetGame(){
    tileStateArray = arrayOf(TileState.BLANK, TileState.BLANK, TileState.BLANK, TileState.BLANK,
        TileState.BLANK, TileState.BLANK, TileState.BLANK, TileState.BLANK, TileState.BLANK)
    xArray = mutableListOf<Int>()
    oArray = mutableListOf<Int>()
    isGameWon = false
    winner = "No winner yet"
    currentPlayer = "X"
}


