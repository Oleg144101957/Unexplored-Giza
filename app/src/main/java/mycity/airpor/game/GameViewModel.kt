package mycity.airpor.game

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import mycity.airpor.R
import java.util.Timer
import java.util.TimerTask

class GameViewModel : ViewModel() {

    val win : MutableStateFlow<Boolean> = MutableStateFlow(false)

    val elementsList : MutableStateFlow<List<Element>> = MutableStateFlow(mutableListOf(
        Element(0, R.drawable.im_8, ElementShape.Rectangle, Color.White),
        Element(1, R.drawable.im_6, ElementShape.Triangle, Color.Red),
        Element(2, R.drawable.im_7, ElementShape.Triangle, Color.Green),
        Element(3, R.drawable.im_5, ElementShape.Triangle, Color.Yellow),

        Element(4, R.drawable.im_8, ElementShape.Triangle, Color.DarkGray),
        Element(5, R.drawable.im_5, ElementShape.Triangle, Color.Green),
        Element(6, R.drawable.im_6, ElementShape.Triangle, Color.Red),
        Element(7, R.drawable.im_5, ElementShape.Triangle, Color.Yellow),

        Element(8, R.drawable.im_7, ElementShape.Triangle, Color.Yellow),
        Element(9, R.drawable.im_8, ElementShape.Triangle, Color.Red),
        Element(10, R.drawable.im_7, ElementShape.Triangle, Color.Yellow),
        Element(11, R.drawable.im_6, ElementShape.Triangle, Color.Green),

        Element(12, R.drawable.im_7, ElementShape.Triangle, Color.Yellow),
        Element(13, R.drawable.im_8, ElementShape.Triangle, Color.Yellow),
        Element(14, R.drawable.im_6, ElementShape.Triangle, Color.Green),
        Element(15, R.drawable.im_5, ElementShape.Triangle, Color.Yellow),

        Element(16, R.drawable.im_5, ElementShape.Triangle, Color.White),
        Element(17, R.drawable.im_7, ElementShape.Triangle, Color.Yellow),
        Element(18, R.drawable.im_5, ElementShape.Triangle, Color.Green),
        Element(19, R.drawable.im_8, ElementShape.Triangle, Color.Yellow),

        Element(20, R.drawable.im_6, ElementShape.Triangle, Color.Yellow),
        Element(21, R.drawable.im_6, ElementShape.Triangle, Color.Yellow),
        Element(22, R.drawable.im_7, ElementShape.Triangle, Color.Red),
        Element(23, R.drawable.im_8, ElementShape.Triangle, Color.Yellow),

        Element(24, R.drawable.im_8, ElementShape.Triangle, Color.Yellow),
        Element(25, R.drawable.im_7, ElementShape.Triangle, Color.Yellow),
        Element(26, R.drawable.im_6, ElementShape.Triangle, Color.White),
        Element(27, R.drawable.im_5, ElementShape.Triangle, Color.Yellow),

        Element(28, R.drawable.im_5, ElementShape.Triangle, Color.Yellow),
        Element(29, R.drawable.im_6, ElementShape.Triangle, Color.Green),
        Element(30, R.drawable.im_7, ElementShape.Triangle, Color.Green),
        Element(31, R.drawable.im_8, ElementShape.Triangle, Color.White),

        Element(32, R.drawable.im_5, ElementShape.Triangle, Color.Green),
        Element(33, R.drawable.im_8, ElementShape.Triangle, Color.Yellow),
        Element(34, R.drawable.im_6, ElementShape.Triangle, Color.Red),
        Element(35, R.drawable.im_8, ElementShape.Triangle, Color.Yellow),

        Element(36, R.drawable.im_5, ElementShape.Triangle, Color.Yellow),
        Element(37, R.drawable.im_6, ElementShape.Triangle, Color.Green),
        Element(38, R.drawable.im_7, ElementShape.Triangle, Color.Yellow),
        Element(39, R.drawable.im_7, ElementShape.Triangle, Color.Red)
    ))

    private val originalElementsList: List<Element> = elementsList.value.toList()


    fun swapElementLeft(element: Int){
        val swapPosition = element - 1
        if (swapPosition in 0..39){
            swapElement(element, swapPosition)
        }
    }

    fun swapElementRight(element: Int){
        val swapPosition = element + 1
        if (swapPosition in 0..39){
            swapElement(element, swapPosition)
        }
    }

    fun swapElementDown(element: Int){
        val swapPosition = element + 4
        if (swapPosition in 0..39){
            swapElement(element, swapPosition)
        }
    }

    fun swapElementUp(element: Int){
        val swapPosition = element - 4
        if (swapPosition in 0..39){
            swapElement(element, swapPosition)
        }
    }


    private fun swapElement(element: Int, swapPosition: Int) {
        val elementCurrent = elementsList.value[swapPosition].image
        val elementToSwap = elementsList.value[element].image
        val newListOfStates = elementsList.value.map { oneElementState ->
            if (oneElementState.id == element){
                oneElementState.copy(image = elementCurrent)
            } else if (oneElementState.id == swapPosition){
                oneElementState.copy(image = elementToSwap)
            } else {
                oneElementState
            }
        }

        viewModelScope.launch {
            delay(250)
            elementsList.emit(newListOfStates)
            delay(300)
            checkWinPossibility()
        }
    }

    fun checkWinPossibility(){
        val column1 = listOf<Int>(
            elementsList.value[0].image,
            elementsList.value[4].image,
            elementsList.value[8].image,
            elementsList.value[12].image,
            elementsList.value[16].image,
            elementsList.value[20].image,
            elementsList.value[24].image,
            elementsList.value[28].image,
            elementsList.value[32].image,
            elementsList.value[36].image,
        )

        val column2 = listOf<Int>(
            elementsList.value[1].image,
            elementsList.value[5].image,
            elementsList.value[9].image,
            elementsList.value[13].image,
            elementsList.value[17].image,
            elementsList.value[21].image,
            elementsList.value[25].image,
            elementsList.value[29].image,
            elementsList.value[33].image,
            elementsList.value[37].image,
        )

        val column3 = listOf<Int>(
            elementsList.value[2].image,
            elementsList.value[6].image,
            elementsList.value[10].image,
            elementsList.value[14].image,
            elementsList.value[18].image,
            elementsList.value[22].image,
            elementsList.value[26].image,
            elementsList.value[30].image,
            elementsList.value[34].image,
            elementsList.value[38].image
        )

        val column4 = listOf<Int>(
            elementsList.value[3].image,
            elementsList.value[7].image,
            elementsList.value[11].image,
            elementsList.value[15].image,
            elementsList.value[19].image,
            elementsList.value[23].image,
            elementsList.value[27].image,
            elementsList.value[31].image,
            elementsList.value[35].image,
            elementsList.value[39].image
        )

        val same1 = column1.all { it == column1[0] }
        val same2 = column2.all { it == column2[0] }
        val same3 = column3.all { it == column3[0] }
        val same4 = column4.all { it == column4[0] }

        val allSame = same1 && same2 && same3 && same4

        if (allSame){
            viewModelScope.launch {
                delay(200)
                win.value = true
            }
        }


    }

    fun recoverList(){
        elementsList.value = originalElementsList.toMutableList()
    }


}