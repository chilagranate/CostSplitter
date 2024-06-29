package com.example.compose
import android.app.Activity
import android.os.Build
import android.view.View
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import com.example.costsplitter.ui.theme.backgroundDark
import com.example.costsplitter.ui.theme.backgroundDarkHighContrast
import com.example.costsplitter.ui.theme.backgroundDarkMediumContrast
import com.example.costsplitter.ui.theme.backgroundLight
import com.example.costsplitter.ui.theme.backgroundLightHighContrast
import com.example.costsplitter.ui.theme.backgroundLightMediumContrast
import com.example.costsplitter.ui.theme.errorContainerDark
import com.example.costsplitter.ui.theme.errorContainerDarkHighContrast
import com.example.costsplitter.ui.theme.errorContainerDarkMediumContrast
import com.example.costsplitter.ui.theme.errorContainerLight
import com.example.costsplitter.ui.theme.errorContainerLightHighContrast
import com.example.costsplitter.ui.theme.errorContainerLightMediumContrast
import com.example.costsplitter.ui.theme.errorDark
import com.example.costsplitter.ui.theme.errorDarkHighContrast
import com.example.costsplitter.ui.theme.errorDarkMediumContrast
import com.example.costsplitter.ui.theme.errorLight
import com.example.costsplitter.ui.theme.errorLightHighContrast
import com.example.costsplitter.ui.theme.errorLightMediumContrast
import com.example.costsplitter.ui.theme.inverseOnSurfaceDark
import com.example.costsplitter.ui.theme.inverseOnSurfaceDarkHighContrast
import com.example.costsplitter.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.example.costsplitter.ui.theme.inverseOnSurfaceLight
import com.example.costsplitter.ui.theme.inverseOnSurfaceLightHighContrast
import com.example.costsplitter.ui.theme.inverseOnSurfaceLightMediumContrast
import com.example.costsplitter.ui.theme.inversePrimaryDark
import com.example.costsplitter.ui.theme.inversePrimaryDarkHighContrast
import com.example.costsplitter.ui.theme.inversePrimaryDarkMediumContrast
import com.example.costsplitter.ui.theme.inversePrimaryLight
import com.example.costsplitter.ui.theme.inversePrimaryLightHighContrast
import com.example.costsplitter.ui.theme.inversePrimaryLightMediumContrast
import com.example.costsplitter.ui.theme.inverseSurfaceDark
import com.example.costsplitter.ui.theme.inverseSurfaceDarkHighContrast
import com.example.costsplitter.ui.theme.inverseSurfaceDarkMediumContrast
import com.example.costsplitter.ui.theme.inverseSurfaceLight
import com.example.costsplitter.ui.theme.inverseSurfaceLightHighContrast
import com.example.costsplitter.ui.theme.inverseSurfaceLightMediumContrast
import com.example.costsplitter.ui.theme.onBackgroundDark
import com.example.costsplitter.ui.theme.onBackgroundDarkHighContrast
import com.example.costsplitter.ui.theme.onBackgroundDarkMediumContrast
import com.example.costsplitter.ui.theme.onBackgroundLight
import com.example.costsplitter.ui.theme.onBackgroundLightHighContrast
import com.example.costsplitter.ui.theme.onBackgroundLightMediumContrast
import com.example.costsplitter.ui.theme.onErrorContainerDark
import com.example.costsplitter.ui.theme.onErrorContainerDarkHighContrast
import com.example.costsplitter.ui.theme.onErrorContainerDarkMediumContrast
import com.example.costsplitter.ui.theme.onErrorContainerLight
import com.example.costsplitter.ui.theme.onErrorContainerLightHighContrast
import com.example.costsplitter.ui.theme.onErrorContainerLightMediumContrast
import com.example.costsplitter.ui.theme.onErrorDark
import com.example.costsplitter.ui.theme.onErrorDarkHighContrast
import com.example.costsplitter.ui.theme.onErrorDarkMediumContrast
import com.example.costsplitter.ui.theme.onErrorLight
import com.example.costsplitter.ui.theme.onErrorLightHighContrast
import com.example.costsplitter.ui.theme.onErrorLightMediumContrast
import com.example.costsplitter.ui.theme.onPrimaryContainerDark
import com.example.costsplitter.ui.theme.onPrimaryContainerDarkHighContrast
import com.example.costsplitter.ui.theme.onPrimaryContainerDarkMediumContrast
import com.example.costsplitter.ui.theme.onPrimaryContainerLight
import com.example.costsplitter.ui.theme.onPrimaryContainerLightHighContrast
import com.example.costsplitter.ui.theme.onPrimaryContainerLightMediumContrast
import com.example.costsplitter.ui.theme.onPrimaryDark
import com.example.costsplitter.ui.theme.onPrimaryDarkHighContrast
import com.example.costsplitter.ui.theme.onPrimaryDarkMediumContrast
import com.example.costsplitter.ui.theme.onPrimaryLight
import com.example.costsplitter.ui.theme.onPrimaryLightHighContrast
import com.example.costsplitter.ui.theme.onPrimaryLightMediumContrast
import com.example.costsplitter.ui.theme.onSecondaryContainerDark
import com.example.costsplitter.ui.theme.onSecondaryContainerDarkHighContrast
import com.example.costsplitter.ui.theme.onSecondaryContainerDarkMediumContrast
import com.example.costsplitter.ui.theme.onSecondaryContainerLight
import com.example.costsplitter.ui.theme.onSecondaryContainerLightHighContrast
import com.example.costsplitter.ui.theme.onSecondaryContainerLightMediumContrast
import com.example.costsplitter.ui.theme.onSecondaryDark
import com.example.costsplitter.ui.theme.onSecondaryDarkHighContrast
import com.example.costsplitter.ui.theme.onSecondaryDarkMediumContrast
import com.example.costsplitter.ui.theme.onSecondaryLight
import com.example.costsplitter.ui.theme.onSecondaryLightHighContrast
import com.example.costsplitter.ui.theme.onSecondaryLightMediumContrast
import com.example.costsplitter.ui.theme.onSurfaceDark
import com.example.costsplitter.ui.theme.onSurfaceDarkHighContrast
import com.example.costsplitter.ui.theme.onSurfaceDarkMediumContrast
import com.example.costsplitter.ui.theme.onSurfaceLight
import com.example.costsplitter.ui.theme.onSurfaceLightHighContrast
import com.example.costsplitter.ui.theme.onSurfaceLightMediumContrast
import com.example.costsplitter.ui.theme.onSurfaceVariantDark
import com.example.costsplitter.ui.theme.onSurfaceVariantDarkHighContrast
import com.example.costsplitter.ui.theme.onSurfaceVariantDarkMediumContrast
import com.example.costsplitter.ui.theme.onSurfaceVariantLight
import com.example.costsplitter.ui.theme.onSurfaceVariantLightHighContrast
import com.example.costsplitter.ui.theme.onSurfaceVariantLightMediumContrast
import com.example.costsplitter.ui.theme.onTertiaryContainerDark
import com.example.costsplitter.ui.theme.onTertiaryContainerDarkHighContrast
import com.example.costsplitter.ui.theme.onTertiaryContainerDarkMediumContrast
import com.example.costsplitter.ui.theme.onTertiaryContainerLight
import com.example.costsplitter.ui.theme.onTertiaryContainerLightHighContrast
import com.example.costsplitter.ui.theme.onTertiaryContainerLightMediumContrast
import com.example.costsplitter.ui.theme.onTertiaryDark
import com.example.costsplitter.ui.theme.onTertiaryDarkHighContrast
import com.example.costsplitter.ui.theme.onTertiaryDarkMediumContrast
import com.example.costsplitter.ui.theme.onTertiaryLight
import com.example.costsplitter.ui.theme.onTertiaryLightHighContrast
import com.example.costsplitter.ui.theme.onTertiaryLightMediumContrast
import com.example.costsplitter.ui.theme.outlineDark
import com.example.costsplitter.ui.theme.outlineDarkHighContrast
import com.example.costsplitter.ui.theme.outlineDarkMediumContrast
import com.example.costsplitter.ui.theme.outlineLight
import com.example.costsplitter.ui.theme.outlineLightHighContrast
import com.example.costsplitter.ui.theme.outlineLightMediumContrast
import com.example.costsplitter.ui.theme.outlineVariantDark
import com.example.costsplitter.ui.theme.outlineVariantDarkHighContrast
import com.example.costsplitter.ui.theme.outlineVariantDarkMediumContrast
import com.example.costsplitter.ui.theme.outlineVariantLight
import com.example.costsplitter.ui.theme.outlineVariantLightHighContrast
import com.example.costsplitter.ui.theme.outlineVariantLightMediumContrast
import com.example.costsplitter.ui.theme.primaryContainerDark
import com.example.costsplitter.ui.theme.primaryContainerDarkHighContrast
import com.example.costsplitter.ui.theme.primaryContainerDarkMediumContrast
import com.example.costsplitter.ui.theme.primaryContainerLight
import com.example.costsplitter.ui.theme.primaryContainerLightHighContrast
import com.example.costsplitter.ui.theme.primaryContainerLightMediumContrast
import com.example.costsplitter.ui.theme.primaryDark
import com.example.costsplitter.ui.theme.primaryDarkHighContrast
import com.example.costsplitter.ui.theme.primaryDarkMediumContrast
import com.example.costsplitter.ui.theme.primaryLight
import com.example.costsplitter.ui.theme.primaryLightHighContrast
import com.example.costsplitter.ui.theme.primaryLightMediumContrast
import com.example.costsplitter.ui.theme.scrimDark
import com.example.costsplitter.ui.theme.scrimDarkHighContrast
import com.example.costsplitter.ui.theme.scrimDarkMediumContrast
import com.example.costsplitter.ui.theme.scrimLight
import com.example.costsplitter.ui.theme.scrimLightHighContrast
import com.example.costsplitter.ui.theme.scrimLightMediumContrast
import com.example.costsplitter.ui.theme.secondaryContainerDark
import com.example.costsplitter.ui.theme.secondaryContainerDarkHighContrast
import com.example.costsplitter.ui.theme.secondaryContainerDarkMediumContrast
import com.example.costsplitter.ui.theme.secondaryContainerLight
import com.example.costsplitter.ui.theme.secondaryContainerLightHighContrast
import com.example.costsplitter.ui.theme.secondaryContainerLightMediumContrast
import com.example.costsplitter.ui.theme.secondaryDark
import com.example.costsplitter.ui.theme.secondaryDarkHighContrast
import com.example.costsplitter.ui.theme.secondaryDarkMediumContrast
import com.example.costsplitter.ui.theme.secondaryLight
import com.example.costsplitter.ui.theme.secondaryLightHighContrast
import com.example.costsplitter.ui.theme.secondaryLightMediumContrast
import com.example.costsplitter.ui.theme.surfaceBrightDark
import com.example.costsplitter.ui.theme.surfaceBrightDarkHighContrast
import com.example.costsplitter.ui.theme.surfaceBrightDarkMediumContrast
import com.example.costsplitter.ui.theme.surfaceBrightLight
import com.example.costsplitter.ui.theme.surfaceBrightLightHighContrast
import com.example.costsplitter.ui.theme.surfaceBrightLightMediumContrast
import com.example.costsplitter.ui.theme.surfaceContainerDark
import com.example.costsplitter.ui.theme.surfaceContainerDarkHighContrast
import com.example.costsplitter.ui.theme.surfaceContainerDarkMediumContrast
import com.example.costsplitter.ui.theme.surfaceContainerHighDark
import com.example.costsplitter.ui.theme.surfaceContainerHighDarkHighContrast
import com.example.costsplitter.ui.theme.surfaceContainerHighDarkMediumContrast
import com.example.costsplitter.ui.theme.surfaceContainerHighLight
import com.example.costsplitter.ui.theme.surfaceContainerHighLightHighContrast
import com.example.costsplitter.ui.theme.surfaceContainerHighLightMediumContrast
import com.example.costsplitter.ui.theme.surfaceContainerHighestDark
import com.example.costsplitter.ui.theme.surfaceContainerHighestDarkHighContrast
import com.example.costsplitter.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.example.costsplitter.ui.theme.surfaceContainerHighestLight
import com.example.costsplitter.ui.theme.surfaceContainerHighestLightHighContrast
import com.example.costsplitter.ui.theme.surfaceContainerHighestLightMediumContrast
import com.example.costsplitter.ui.theme.surfaceContainerLight
import com.example.costsplitter.ui.theme.surfaceContainerLightHighContrast
import com.example.costsplitter.ui.theme.surfaceContainerLightMediumContrast
import com.example.costsplitter.ui.theme.surfaceContainerLowDark
import com.example.costsplitter.ui.theme.surfaceContainerLowDarkHighContrast
import com.example.costsplitter.ui.theme.surfaceContainerLowDarkMediumContrast
import com.example.costsplitter.ui.theme.surfaceContainerLowLight
import com.example.costsplitter.ui.theme.surfaceContainerLowLightHighContrast
import com.example.costsplitter.ui.theme.surfaceContainerLowLightMediumContrast
import com.example.costsplitter.ui.theme.surfaceContainerLowestDark
import com.example.costsplitter.ui.theme.surfaceContainerLowestDarkHighContrast
import com.example.costsplitter.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.example.costsplitter.ui.theme.surfaceContainerLowestLight
import com.example.costsplitter.ui.theme.surfaceContainerLowestLightHighContrast
import com.example.costsplitter.ui.theme.surfaceContainerLowestLightMediumContrast
import com.example.costsplitter.ui.theme.surfaceDark
import com.example.costsplitter.ui.theme.surfaceDarkHighContrast
import com.example.costsplitter.ui.theme.surfaceDarkMediumContrast
import com.example.costsplitter.ui.theme.surfaceDimDark
import com.example.costsplitter.ui.theme.surfaceDimDarkHighContrast
import com.example.costsplitter.ui.theme.surfaceDimDarkMediumContrast
import com.example.costsplitter.ui.theme.surfaceDimLight
import com.example.costsplitter.ui.theme.surfaceDimLightHighContrast
import com.example.costsplitter.ui.theme.surfaceDimLightMediumContrast
import com.example.costsplitter.ui.theme.surfaceLight
import com.example.costsplitter.ui.theme.surfaceLightHighContrast
import com.example.costsplitter.ui.theme.surfaceLightMediumContrast
import com.example.costsplitter.ui.theme.surfaceVariantDark
import com.example.costsplitter.ui.theme.surfaceVariantDarkHighContrast
import com.example.costsplitter.ui.theme.surfaceVariantDarkMediumContrast
import com.example.costsplitter.ui.theme.surfaceVariantLight
import com.example.costsplitter.ui.theme.surfaceVariantLightHighContrast
import com.example.costsplitter.ui.theme.surfaceVariantLightMediumContrast
import com.example.costsplitter.ui.theme.tertiaryContainerDark
import com.example.costsplitter.ui.theme.tertiaryContainerDarkHighContrast
import com.example.costsplitter.ui.theme.tertiaryContainerDarkMediumContrast
import com.example.costsplitter.ui.theme.tertiaryContainerLight
import com.example.costsplitter.ui.theme.tertiaryContainerLightHighContrast
import com.example.costsplitter.ui.theme.tertiaryContainerLightMediumContrast
import com.example.costsplitter.ui.theme.tertiaryDark
import com.example.costsplitter.ui.theme.tertiaryDarkHighContrast
import com.example.costsplitter.ui.theme.tertiaryDarkMediumContrast
import com.example.costsplitter.ui.theme.tertiaryLight
import com.example.costsplitter.ui.theme.tertiaryLightHighContrast
import com.example.costsplitter.ui.theme.tertiaryLightMediumContrast
import com.example.ui.theme.Typography

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun CostSplitterTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable() () -> Unit
) {
  val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      
      darkTheme -> darkScheme
      else -> lightScheme
  }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Establecer el color de fondo de la barra de estado
            window.statusBarColor = colorScheme.background.toArgb()
            //window.navigationBarColor = colorScheme.background.toArgb()

            // Configurar los íconos y texto de la barra de estado según el tema
            if (darkTheme) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                window.decorView.systemUiVisibility = 0
            }
        }
    }

  MaterialTheme(
    colorScheme = colorScheme,
      typography = Typography,
    content = content
  )
}

