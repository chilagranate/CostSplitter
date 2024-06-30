package com.example.costsplitter

import android.annotation.SuppressLint
import android.graphics.drawable.AdaptiveIconDrawable
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.CostSplitterTheme
import com.example.costsplitter.ui.navigation.CostSplitterNavHost
import com.example.costsplitter.ui.screens.home.BottomNavItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CostSplitterApp(navController: NavHostController = rememberNavController()) {
    CostSplitterTheme {
        CostSplitterNavHost(navController = navController)
    }
}


/**
 * App bar to display title and conditionally display the back navigation.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CostSplitterTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        })
}


@Composable
fun AppNavigationBar(
    items: List<BottomNavItem>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    NavigationBar(containerColor = MaterialTheme.colorScheme.background) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = selectedItemIndex == index,
                onClick = { onItemSelected(index) },
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun CostSplitterTopAppBarPreview() {
    CostSplitterTopAppBar(title = "Preview", canNavigateBack = true)
}

@Composable
fun adaptiveIconPainterResource(@DrawableRes id: Int): Painter {
    val res = LocalContext.current.resources
    val theme = LocalContext.current.theme

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        // Android O supports adaptive icons, try loading this first (even though this is least likely to be the format).
        val adaptiveIcon = ResourcesCompat.getDrawable(res, id, theme) as? AdaptiveIconDrawable
        if (adaptiveIcon != null) {
            BitmapPainter(adaptiveIcon.toBitmap().asImageBitmap())
        } else {
            // We couldn't load the drawable as an Adaptive Icon, just use painterResource
            painterResource(id)
        }
    } else {
        // We're not on Android O or later, just use painterResource
        painterResource(id)
    }
}

@Composable
@Preview
fun AppCircularProgressIndicator(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()){

    CircularProgressIndicator(
        modifier = Modifier.size(64.dp).align(Alignment.Center),
        color = MaterialTheme.colorScheme.primary,
        strokeWidth = 4.dp

    )
    }

}

@Composable
@Preview
fun IconApp(modifier: Modifier = Modifier) {
    Image(
        painter = adaptiveIconPainterResource(id = R.mipmap.ic_launcher),
        contentDescription = null,
        modifier = modifier.requiredSize(128.dp)
    )
}

