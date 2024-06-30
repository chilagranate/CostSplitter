package com.example.costsplitter.ui.screens.groups

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.costsplitter.AppCircularProgressIndicator
import com.example.costsplitter.R
import com.example.costsplitter.data.model.Group

@Composable
fun GroupsScreen(
    viewModel: GroupsScreenViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    if(uiState.isLoading){
        AppCircularProgressIndicator()
    }else {
        GroupsScreenBody(
            groups = uiState.groups,
        )
    }
}

@Composable
fun GroupsScreenBody(
    onGroupClick: (Int) -> Unit = {},
    onAddExpenseClick: (Int) -> Unit = {},
    groups: List<Group> = emptyList(),
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(groups.size) { i ->
                GroupCard(
                    group = groups.elementAt(i),
                    onClick = { onGroupClick(i) },
                    onAddExpenseClick = { onAddExpenseClick(i) }
                )
            }
        }
    }
}

@Composable
fun GroupCard(
    group: Group,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    onAddExpenseClick: () -> Unit = {}

    ){
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(150.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del grupo
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.group_image_placeholder),

                    contentDescription = "Group Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }


            //Spacer(modifier = Modifier.width(16.dp))


            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(8.dp)
                    .weight(1f)
                ,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Nombre del grupo
                Text(
                    text = group.name,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // TODO Saldo del grupo
//                Text(
//                    text = if (group.balance > 0) "Te deben $${group.balance}" else "Debes $${-group.balance}",
//                    color = if (group.balance > 0) Color.Green else MaterialTheme.colorScheme.error,
//                    style = MaterialTheme.typography.bodyLarge,
//                    maxLines = 1,
//                    overflow = TextOverflow.Ellipsis
//                )

                // Botón para agregar gasto
                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
                    onClick = onAddExpenseClick,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text("Agregar Gasto")
                }
            }
        }
    }
}


//@Composable
//@Preview
//fun GroupCardPreview(){
//    GroupCard(
//        group = Group("Grupo 1"),
//        )
//}