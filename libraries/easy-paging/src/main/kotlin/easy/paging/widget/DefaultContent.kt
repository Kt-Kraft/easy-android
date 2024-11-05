package easy.paging.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
public fun DefaultLoadingContent() {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(40.dp),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    CircularProgressIndicator(
      color = Color.Red,
      modifier = Modifier.size(25.dp),
      strokeWidth = 2.dp,
    )
  }
}

@Composable
public fun DefaultNoMoreContent(
  noMoreText: String = "No More Data",
) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(40.dp)
      .padding(vertical = 10.dp),
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically,
  ) {
    Text(
      text = noMoreText,
      modifier = Modifier
        .padding(horizontal = 5.dp),
      textAlign = TextAlign.Center,
    )
  }
}

@Composable
public fun DefaultErrorContent(
  errorText: String? = "NetWork Error!",
  retry: (() -> Unit)?,
) {
  Text(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 10.dp)
      .clickable {
        retry?.invoke()
      },
    text = errorText ?: "Some Error!",
    textAlign = TextAlign.Center,
  )
}

@Composable
public fun DefaultRefreshingContent() {
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    DefaultLoadingContent()
  }
}

@Composable
public fun DefaultFirstLoadErrorContent(
  errorText: String? = "NetWork Error!",
  retry: (() -> Unit)?,
) {
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(text = errorText ?: "NetWork Error")
    retry?.let {
      Button(
        modifier = Modifier.padding(top = 20.dp),
        onClick = retry,
      ) {
        Text(text = "Retry")
      }
    }
  }
}

@Composable
public fun DefaultEmptyListContent(
  text: String = "Empty List",
) {
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
  ) {
    Text(text = text, fontSize = 18.sp)
  }
}

@Preview(name = "DefaultLoadingContent")
@Composable
private fun P1() {
  DefaultLoadingContent()
}

@Preview(name = "DefaultNoMoreContent")
@Composable
private fun P2() {
  DefaultNoMoreContent()
}

@Preview(name = "DefaultErrorContent")
@Composable
private fun P3() {
  DefaultErrorContent {}
}

@Preview(name = "DefaultRefreshingContent")
@Composable
private fun P4() {
  DefaultRefreshingContent()
}

@Preview(name = "DefaultFirstLoadErrorContent")
@Composable
private fun P5() {
  DefaultFirstLoadErrorContent {}
}

@Preview(name = "DefaultEmptyListContent")
@Composable
private fun P6() {
  DefaultEmptyListContent()
}
