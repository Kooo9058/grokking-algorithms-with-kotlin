package utilities

abstract class CounterOperations: ICounterEstimate {
    private var counterOperations = 0

    override fun startCount() {
        counterOperations++
    }

    override fun endAndPrintCount(tag: String) {
        println("Counter operation is $counterOperations Tag $tag")
        counterOperations = 0
    }
}