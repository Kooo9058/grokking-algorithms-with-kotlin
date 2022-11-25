package utilities

interface ICounterEstimate {
    fun startCount()
    fun endAndPrintCount(tag: String = "")
}