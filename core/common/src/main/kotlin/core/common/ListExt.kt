package core.common

public fun <T> List<T>.rotate(distance: Int): List<T> {
  val listSize = this.size
  if (listSize == 0) return this
  val normalizedDistance = (distance % listSize + listSize) % listSize
  return this.drop(normalizedDistance) + this.take(normalizedDistance)
}
