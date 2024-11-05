package easy.paging.widget

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

@Composable
public fun <T : Any> PagingListContainer(
  pagingData: LazyPagingItems<T>,
  refreshingContent: @Composable (() -> Unit)? = { DefaultRefreshingContent() },
  firstLoadErrorContent: @Composable ((error: Throwable, retry: (() -> Unit)?) -> Unit)? = { error, retry ->
    DefaultFirstLoadErrorContent(error.message, retry)
  },
  emptyListContent: @Composable (() -> Unit)? = { DefaultEmptyListContent() },
  listContent: @Composable () -> Unit,
) {
  when (val state = pagingData.loadState.refresh) {
    is LoadState.Loading -> refreshingContent?.invoke()
    is LoadState.Error -> firstLoadErrorContent?.invoke(state.error, pagingData::retry)
    is LoadState.NotLoading -> if (pagingData.itemCount == 0) emptyListContent?.invoke()
    else -> listContent()
  }
}
