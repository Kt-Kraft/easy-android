package feature.homepage.screen.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import core.designSystem.theme.LocalTypography
import kotlinx.coroutines.flow.Flow

@Composable
public fun DashboardScreen(
  uiState: DashboardContract.UiState,
  sideEffect: Flow<DashboardContract.SideEffect>,
  onAction: (DashboardContract.UiAction) -> Unit,
) {
  val snackBarHostState = remember { SnackbarHostState() }

  Scaffold(
    snackbarHost = { SnackbarHost(snackBarHostState) },
  ) { paddingValues ->
    Box(
      modifier = Modifier
        .padding(paddingValues)
        .fillMaxSize(),
    ) {
      Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp)
      ) {
        Column(
          modifier = Modifier.padding(16.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {
          AsyncImage(
            model = "https://drive.usercontent.google.com/download?id=1slq2O4ZChlcuvsNk7ZM3wP8_Webc7ua1",
            contentDescription = "Profile Picture",
            modifier = Modifier
              .size(192.dp, 228.dp)
              .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
          )

          Spacer(modifier = Modifier.height(12.dp))

          Text(
            text = "Indra Mahkota",
            style = LocalTypography.current.header18Bold,
          )

          Spacer(modifier = Modifier.height(8.dp))

          Text(
            text = "Android Software Engineer\nSkilled in Kotlin, Jetpack Compose, React Native, and Flutter.",
            style = LocalTypography.current.body14Regular,
            textAlign = TextAlign.Center,
          )

          Spacer(modifier = Modifier.height(12.dp))

          Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(top = 8.dp)
          ) {
            IconButton(onClick = { }) {
              Icon(Icons.Default.Share, contentDescription = "Share", tint = Color(0xFFFF5722))
            }
            IconButton(onClick = { }) {
              Icon(Icons.Default.Favorite, contentDescription = "Favorite", tint = Color(0xFF1DA1F2))
            }
            IconButton(onClick = { }) {
              Icon(Icons.Default.Email, contentDescription = "Message", tint = Color(0xFFE4405F))
            }
          }
        }
      }
    }
  }
}
