package easy.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import easy.paging.config.GlobalPagingConfig
import easy.paging.config.toPagingConfig

public fun <K : Any, V : Any> basePager(
  pagerConfig: GlobalPagingConfig = GlobalPagingConfig(),
  initialKey: K,
  loadData: suspend (params: PagingSource.LoadParams<K>) -> PagingSource.LoadResult<K, V>,
): Flow<PagingData<V>> {
  val mPagerConfig = pagerConfig.toPagingConfig()
  return Pager(
    config = mPagerConfig,
    initialKey = initialKey,
    pagingSourceFactory = {
      object : PagingSource<K, V>() {
        override fun getRefreshKey(state: PagingState<K, V>): K {
          return initialKey
        }

        override suspend fun load(params: LoadParams<K>): LoadResult<K, V> {
          return loadData(params)
        }
      }
    },
  ).flow
}

public fun <K : Any, V : Any> ViewModel.basePager(
  pagerConfig: GlobalPagingConfig = GlobalPagingConfig(),
  initialKey: K,
  loadData: suspend (params: PagingSource.LoadParams<K>) -> PagingSource.LoadResult<K, V>,
): Flow<PagingData<V>> {
  return easy.paging.basePager(pagerConfig, initialKey, loadData)
    .cachedIn(viewModelScope)
}
