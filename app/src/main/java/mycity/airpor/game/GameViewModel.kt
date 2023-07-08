package mycity.airpor.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import mycity.airpor.R

class GameViewModel : ViewModel() {

    val elementsList : MutableStateFlow<List<Element>> = MutableStateFlow(mutableListOf(
        Element(0, R.drawable.im_5),
        Element(1, R.drawable.im_6),
        Element(2, R.drawable.im_7),
        Element(3, R.drawable.im_8),
        Element(4, R.drawable.im_9),
        Element(5, R.drawable.im_5),
        Element(6, R.drawable.im_6),
        Element(7, R.drawable.im_7),
        Element(8, R.drawable.im_8),
        Element(9, R.drawable.im_9),
        Element(10, R.drawable.im_5),
        Element(11, R.drawable.im_6),
        Element(12, R.drawable.im_7),
        Element(13, R.drawable.im_8),
        Element(14, R.drawable.im_9),
        Element(15, R.drawable.im_5),
        Element(16, R.drawable.im_6),
        Element(17, R.drawable.im_7),
        Element(18, R.drawable.im_8),
        Element(19, R.drawable.im_9),
        Element(20, R.drawable.im_5),
        Element(21, R.drawable.im_6),
        Element(22, R.drawable.im_7),
        Element(23, R.drawable.im_8),
        Element(24, R.drawable.im_9),
        Element(25, R.drawable.im_5),
        Element(26, R.drawable.im_6),
        Element(27, R.drawable.im_7),
        Element(28, R.drawable.im_8),
        Element(29, R.drawable.im_9),
        Element(30, R.drawable.im_9),
        Element(31, R.drawable.im_9),
        Element(32, R.drawable.im_9),
        Element(33, R.drawable.im_9),
        Element(34, R.drawable.im_9),
        Element(35, R.drawable.im_9),
        Element(36, R.drawable.im_9),
        Element(37, R.drawable.im_9),
        Element(38, R.drawable.im_9),
        Element(39, R.drawable.im_9)
    ))


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
        }
    }
}