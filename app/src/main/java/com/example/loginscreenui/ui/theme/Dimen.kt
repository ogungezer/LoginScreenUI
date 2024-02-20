package com.example.loginscreenui.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimen(
    val small1 : Dp = 0.dp,
    val small2 : Dp = 0.dp,
    val small3 : Dp = 0.dp,
    val medium1 : Dp = 0.dp,
    val medium2 : Dp = 0.dp,
    val medium3 : Dp = 0.dp,
    val large : Dp = 0.dp,
    val buttonHeight : Dp = 40.dp,
    val logoSize: Dp = 50.dp
)

val CompactSmallDimens = Dimen(
    small1 = 7.dp,
    small2 = 9.dp,
    small3 = 13.dp,
    medium1 = 15.dp,
    medium2 = 26.dp,
    medium3 = 32.dp,
    large = 45.dp
)

val CompactMediumDimens = Dimen(
    small1 = 8.dp,
    small2 = 15.dp,
    small3 = 17.dp,
    medium1 = 25.dp,
    medium2 = 30.dp,
    medium3 = 38.dp,
    large = 65.dp
)

val CompactDimens = Dimen(
    small1 = 10.dp,
    small2 = 16.dp,
    small3 = 20.dp,
    medium1 = 30.dp,
    medium2 = 36.dp,
    medium3 = 40.dp,
    large = 80.dp,
)

val MediumDimens = Dimen(
    small1 = 10.dp,
    small2 = 16.dp,
    small3 = 20.dp,
    medium1 = 30.dp,
    medium2 = 36.dp,
    medium3 = 40.dp,
    large = 110.dp,
    logoSize = 55.dp
)

val ExpandedDimens = Dimen(
    small1 = 15.dp,
    small2 = 20.dp,
    small3 = 25.dp,
    medium1 = 35.dp,
    medium2 = 40.dp,
    medium3 = 45.dp,
    large = 130.dp,
    logoSize = 72.dp
)