package org.cate.hunterking.tictactoe

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import checkWinCondition
import currentPlayer
import gameMove
import isGameWon
import kotlinx.android.synthetic.main.activity_view.*
import resetGame
import switchPlayer
import tileStateArray
import winner


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        //Interacts with TicTacToe model to check the state of tile and change according to current player
        fun tileButtonPress (tileIndex: Int, button: Button){

            //First if statement checks to see if the tile is playable (can't change state of tile that isn't blank)
            if (tileStateArray[tileIndex] == TileState.BLANK && !isGameWon){

                //Stores the tile that was clicked in the model
                gameMove(tileIndex)
                button.text = currentPlayer

                checkWinCondition()
                if (isGameWon){
                    playerTextView.text = "GAME OVER! \n The winner is $winner"
                    resetButton.setBackgroundColor(Color.parseColor("#d81b60"))
                    resetButton.setTextColor(Color.parseColor("#ffffff"))
                } else {
                    switchPlayer()
                    playerTextView.text = "Current player: $currentPlayer"
                }
            }
        }

        tile0.setOnClickListener {tileButtonPress(0,tile0)}
        tile1.setOnClickListener {tileButtonPress(1,tile1)}
        tile2.setOnClickListener {tileButtonPress(2,tile2)}
        tile3.setOnClickListener {tileButtonPress(3,tile3)}
        tile4.setOnClickListener {tileButtonPress(4,tile4)}
        tile5.setOnClickListener {tileButtonPress(5,tile5)}
        tile6.setOnClickListener {tileButtonPress(6,tile6)}
        tile7.setOnClickListener {tileButtonPress(7,tile7)}
        tile8.setOnClickListener {tileButtonPress(8,tile8)}

        //Tile array used for iterating in the reset function (so not every button has to be cleared individually)
        val tileArray = arrayOf(tile0, tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8)

        //Resets all the game variables in the model, clears all the button texts
        resetButton.setOnClickListener {
            resetGame()
            playerTextView.text = "Current player: $currentPlayer"

            for (index in 0..(tileArray.size-1)){
            tileArray[index].text = ""
            }
            resetButton.setBackgroundColor(Color.parseColor("#d6d7d7"))
            resetButton.setTextColor(Color.parseColor("#e6616161"))
        }
    }
}
