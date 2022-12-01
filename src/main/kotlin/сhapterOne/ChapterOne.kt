package сhapterOne

import data.DataCreator
import utilities.CounterOperations

fun main() {
    val listData = DataCreator().getListOddNumbers(size = 5000)
    val algorithms = SearchingAlgorithms(list = listData)
    val itemNeedFind = 4999
    val indexB = algorithms.binarySearchingItemsIndex(item = itemNeedFind)
    val indexL = algorithms.linealSearchingItemsIndex(item = itemNeedFind)

    println("binary searching index item is $indexB")
    println("lineal searching index item is $indexL")
}

class SearchingAlgorithms(private val list: List<Int>): CounterOperations() {

    fun binarySearchingItemsIndex(item: Int): Int {
        var low = 0
        var high = list.size

        while (low <= high) {
            startCount()
            val mid = (low + high) / 2
            val guard = list.getOrNull(mid) ?: break
            if (guard == item) {
                endAndPrintCount("binary")
                return mid
            }
            if (guard > item) {
                high = mid - 1
            } else {
                low = mid + 1
            }
        }
        endAndPrintCount()
        return -1
    }

    fun linealSearchingItemsIndex(item: Int): Int {
        for (i in list.indices) {
            startCount()
            if (list[i] == item) {
                endAndPrintCount("lineal")
                return i
            }
        }
        return -1
    }
//    fun linealSearchingItemsIndex(item: Int): Int = list.find { it == item } ?: -1
}