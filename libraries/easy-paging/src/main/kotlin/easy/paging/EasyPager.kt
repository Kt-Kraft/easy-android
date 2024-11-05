package easy.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import easy.paging.config.GlobalPagingConfig
import easy.paging.model.PaginationListModel

public fun <K : Any, V : Any> easyPager(
  pagerConfig: GlobalPagingConfig = GlobalPagingConfig(),
  initialKey: K,
  loadData: suspend (page: K) -> PaginationListModel<K, V>,
): Flow<PagingData<V>> {
  return pager(pagerConfig, initialKey) { page ->
    return@pager runCatching {
      Result.success(loadData(page))
    }.getOrElse { exception ->
      Result.failure(exception)
    }
  }
}

public fun <K : Any, V : Any> ViewModel.easyPager(
  pagerConfig: GlobalPagingConfig = GlobalPagingConfig(),
  initialKey: K,
  loadData: suspend (page: K) -> PaginationListModel<K, V>,
): Flow<PagingData<V>> {
  return easy.paging.easyPager(pagerConfig, initialKey, loadData)
    .cachedIn(viewModelScope)
}
