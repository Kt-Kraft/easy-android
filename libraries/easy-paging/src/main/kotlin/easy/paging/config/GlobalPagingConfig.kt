package easy.paging.config

import androidx.paging.PagingConfig

public data class GlobalPagingConfig(
  val pageSize: Int = 10,
  val initialLoadSize: Int = 10,
  val prefetchDistance: Int = 1,
  val maxSize: Int = PagingConfig.MAX_SIZE_UNBOUNDED,
  val enablePlaceholders: Boolean = false,
)

public fun GlobalPagingConfig.toPagingConfig(): PagingConfig {
  return PagingConfig(
    this.pageSize,
    initialLoadSize = this.initialLoadSize,
    prefetchDistance = this.prefetchDistance,
    maxSize = this.maxSize,
    enablePlaceholders = this.enablePlaceholders,
  )
}
