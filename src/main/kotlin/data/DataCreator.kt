package data

import kotlin.random.Random

class DataCreator {
    fun getRandomList(rangeNumber: Int, sizeList: Int): MutableList<Int> {
        val intMutableList = mutableListOf<Int>()
        for (r in 0 until sizeList) {
            val randomInt = Random.nextInt(0, rangeNumber)
            intMutableList.add(randomInt)
        }
        return intMutableList
    }

    fun getListNaturalNumbers(size: Int) : MutableList<Int> {
        val intMutableList = mutableListOf<Int>()
        for (currentIndex in 0 until size) {
            intMutableList.add(currentIndex)
        }
        return intMutableList
    }

    fun getListEventNumbers(size: Int) : MutableList<Int> {
        val intMutableList = mutableListOf<Int>()
        for (currentIndex in 0 until size) {
            if (currentIndex % 2 == 0) intMutableList.add(currentIndex)
        }
        return intMutableList
    }

    fun getListOddNumbers(size: Int) : MutableList<Int> {
        val intMutableList = mutableListOf<Int>()
        for (currentIndex in 0 until size) {
            if (currentIndex % 2 != 0) intMutableList.add(currentIndex)
        }
        return intMutableList
    }
}