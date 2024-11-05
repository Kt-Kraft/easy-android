package easy.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import easy.paging.model.PaginationListModel
import easy.paging.config.GlobalPagingConfig

public fun <K : Any, V : Any> pager(
  pagerConfig: GlobalPagingConfig = GlobalPagingConfig(),
  initialKey: K,
  loadData: suspend (page: K) -> Result<PaginationListModel<K, V>?>,
): Flow<PagingData<V>> {
  return basePager(pagerConfig, initialKey) { params ->
    val page = params.key
      ?: return@basePager PagingSource.LoadResult.Error(NullPointerException("Null Key"))

    val result = try {
      loadData(page)
    } catch (exception: Exception) {
      return@basePager PagingSource.LoadResult.Error(exception)
    }

    return@basePager result.fold(
      onSuccess = { response ->
        response?.let {
          PagingSource.LoadResult.Page(
            data = it.getList(),
            prevKey = it.getPreKey(),
            nextKey = if (it.hasMore() && it.getList().isNotEmpty()) it.getNextKey() else null,
          )
        } ?: PagingSource.LoadResult.Page(
          data = emptyList(),
          prevKey = null,
          nextKey = null,
        )
      },
      onFailure = { exception ->
        PagingSource.LoadResult.Error(exception)
      },
    )
  }
}

public fun <K : Any, V : Any> ViewModel.pager(
  pagerConfig: GlobalPagingConfig = GlobalPagingConfig(),
  initialKey: K,
  loadData: suspend (page: K) -> Result<PaginationListModel<K, V>?>,
): Flow<PagingData<V>> {
  return easy.paging.pager(pagerConfig, initialKey, loadData)
    .cachedIn(viewModelScope)
}
