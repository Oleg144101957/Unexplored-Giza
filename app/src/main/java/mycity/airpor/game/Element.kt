package mycity.airpor.game

import androidx.compose.ui.graphics.Color

data class Element(
    val id: Int,
    val image: Int,
    val shape: ElementShape,
    val color: Color
)

sealed class ElementShape(val description: String) {
    object Rectangle : ElementShape("rectangle")
    object Triangle : ElementShape("triangle")
}
