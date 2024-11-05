package easy.paging.widget

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

public fun <T : Any> LazyListScope.itemPaging(
  pagingData: LazyPagingItems<T>,
  loadingContent: @Composable (() -> Unit)? = { DefaultLoadingContent() },
  noMoreContent: @Composable (() -> Unit)? = { DefaultNoMoreContent() },
  errorContent: @Composable ((error: Throwable, retry: (() -> Unit)?) -> Unit)? = { error, retry ->
    DefaultErrorContent(error.message, retry)
  },
) {
  when (val loadState = pagingData.loadState.append) {
    is LoadState.Loading -> loadingContent?.let { item { it() } }

    is LoadState.Error -> errorContent?.let { error ->
      item { error(loadState.error) { pagingData.retry() } }
    }

    is LoadState.NotLoading -> if (loadState.endOfPaginationReached) {
      noMoreContent?.let { item { it() } }
    }
  }
}
