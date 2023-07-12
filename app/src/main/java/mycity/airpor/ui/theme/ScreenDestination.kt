package mycity.airpor.ui.theme

sealed class ScreenDestination (val endpoint: String) {
    object OneScreen : ScreenDestination("one_screen")
    object TwoScreen : ScreenDestination("two_screen")
    object ThreeScreen : ScreenDestination("three_screen")
    object FourScreen : ScreenDestination("four_screen")
    object FiveScreen : ScreenDestination("five_screen")
    object SixScreen : ScreenDestination("six_screen")
}