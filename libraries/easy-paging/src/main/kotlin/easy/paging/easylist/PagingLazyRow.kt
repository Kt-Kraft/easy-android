package easy.paging.easylist

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import easy.paging.widget.DefaultEmptyListContent
import easy.paging.widget.DefaultErrorContent
import easy.paging.widget.DefaultFirstLoadErrorContent
import easy.paging.widget.DefaultLoadingContent
import easy.paging.widget.DefaultNoMoreContent
import easy.paging.widget.DefaultRefreshingContent
import easy.paging.widget.PagingListContainer
import easy.paging.widget.itemPaging

/**
 * Simple use case, not suitable for items with key
 * */
@Composable
public fun <T : Any> PagingLazyRow(
  modifier: Modifier = Modifier,
  pagingData: LazyPagingItems<T>,
  loadingContent: @Composable (() -> Unit)? = { DefaultLoadingContent() },
  noMoreContent: @Composable (() -> Unit)? = { DefaultNoMoreContent() },
  errorContent: @Composable ((error: Throwable, retry: (() -> Unit)?) -> Unit)? = { error, retry ->
    DefaultErrorContent(error.message, retry)
  },
  refreshingContent: @Composable (() -> Unit)? = { DefaultRefreshingContent() },
  firstLoadErrorContent: @Composable ((error: Throwable, retry: (() -> Unit)?) -> Unit)? = { error, retry ->
    DefaultFirstLoadErrorContent(error.message, retry)
  },
  emptyListContent: @Composable (() -> Unit)? = { DefaultEmptyListContent() },
  pagingItemContent: @Composable (value: T?) -> Unit,
) {
  PagingListContainer(pagingData, refreshingContent, firstLoadErrorContent, emptyListContent) {
    LazyRow(modifier) {
      items(pagingData.itemCount) { pagingItemContent(pagingData[it]) }
      itemPaging(pagingData, loadingContent, noMoreContent, errorContent)
    }
  }
}

/**
 * More complex use case
 * */
@Composable
public fun <T : Any> PagingLazyRow(
  modifier: Modifier = Modifier,
  pagingData: LazyPagingItems<T>,
  loadingContent: @Composable (() -> Unit)? = { DefaultLoadingContent() },
  noMoreContent: @Composable (() -> Unit)? = { DefaultNoMoreContent() },
  errorContent: @Composable ((error: Throwable, retry: (() -> Unit)?) -> Unit)? = { error, retry ->
    DefaultErrorContent(error.message, retry)
  },
  refreshingContent: @Composable (() -> Unit)? = { DefaultRefreshingContent() },
  firstLoadErrorContent: @Composable ((error: Throwable, retry: (() -> Unit)?) -> Unit)? = { error, retry ->
    DefaultFirstLoadErrorContent(error.message, retry)
  },
  emptyListContent: @Composable (() -> Unit)? = { DefaultEmptyListContent() },
  state: LazyListState = rememberLazyListState(),
  contentPadding: PaddingValues = PaddingValues(0.dp),
  reverseLayout: Boolean = false,
  horizontalArrangement: Arrangement.Horizontal = if (!reverseLayout) Arrangement.Start else Arrangement.End,
  verticalAlignment: Alignment.Vertical = Alignment.Top,
  flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
  userScrollEnabled: Boolean = true,
  content: LazyListScope.() -> Unit,
) {
  PagingListContainer(
    pagingData = pagingData,
    refreshingContent = refreshingContent,
    firstLoadErrorContent = firstLoadErrorContent,
    emptyListContent = emptyListContent,
  ) {
    LazyRow(
      modifier = modifier,
      state = state,
      contentPadding = contentPadding,
      reverseLayout = reverseLayout,
      horizontalArrangement = horizontalArrangement,
      verticalAlignment = verticalAlignment,
      flingBehavior = flingBehavior,
      userScrollEnabled = userScrollEnabled,
    ) {
      content(this)
      itemPaging(pagingData, loadingContent, noMoreContent, errorContent)
    }
  }
}
