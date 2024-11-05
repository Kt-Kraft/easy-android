package easy.paging.model

public interface PaginationListModel<K, V> {

  public fun getList(): List<V> = emptyList()

  public fun getPreKey(): K? = null

  public fun getNextKey(): K? = null

  public fun hasMore(): Boolean = false
}
