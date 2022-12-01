
# Глава 1: Знакомство с алгоритмами.
В данной статье нас знакомят с алгоритмами, а именно бинарным поиском и сравнивают его эффективность с линейным.
## Теория бинарного поиска
Бинарный поиск работает по следующему принципу:
- Массив делится пополам. Значение элемента в середине массива, берётся за опорный.
![binary_searching_one](https://github.com/Kooo9058/grokking-algorithms-with-kotlin/raw/test-add-readme-for-chapterOne/src/main/image/imageBinaryOne.png)
- Дальше мы сравниваем значение опорного элемента со значением элементра, который необзодимо найти.
![binary_searching_two](https://github.com/Kooo9058/grokking-algorithms-with-kotlin/raw/test-add-readme-for-chapterOne/src/main/image/imageBinaryTwo.png)
- Если значение опорного элемента меньше, то левая часть массива отбрасывается и за исходный берётся правая часть массива и наоборот.
![binary_searching_tree](https://github.com/Kooo9058/grokking-algorithms-with-kotlin/raw/test-add-readme-for-chapterOne/src/main/image/imageBinaryThree.png)
- После задание новых границ исходного мыссива, после чего всё шаги повторяются, до тех пор, пока значение опорного элемента не будет равно значению искомого.
![binary_searching_four](https://github.com/Kooo9058/grokking-algorithms-with-kotlin/raw/test-add-readme-for-chapterOne/src/main/image/imageBinaryFour.png)

 Из-за того, что мы сравниваем значение опорного элемента и искомого, вытекает необходимое условие сортировки массива.

## Скорость работы такого массива
За счёт того, что бинарный поиск откидывает половину массива за каждую итерацию, скорость такого алгоритма O(log(n)),
в то время как скорость обычного поиска линейна O(log(n)). Другими словами, при увеличении размера массива линейному поиску потребуется
проверить все элементы, а бинарному намного меньше.
![binary_searching_four](https://github.com/Kooo9058/grokking-algorithms-with-kotlin/raw/test-add-readme-for-chapterOne/src/main/image/graphicsFunctionsSearchingOne.png)

## Практика бинарного поиска
Рассмотрим метод binarySearchingItemsIndex. Он принимает значение элемента для поиска и возвращает индекс элемента, который мы ищем.
Сначала подготавливаем данные для массива и инициализируем объект класса SearchingAlgorithms.
itemNeedFind - значение переменной, которую нам необходимо найти. indexB и indexL индексы
массива, которые указывают на искомый элемент.
```kotlin
val listData = DataCreator().getListOddNumbers(size = 5000)
val algorithms = SearchingAlgorithms(list = listData)
val itemNeedFind = 4999
val indexB = algorithms.binarySearchingItemsIndex(item = itemNeedFind)
val indexL = algorithms.linealSearchingItemsIndex(item = itemNeedFind)
```
 
Далее мы создаем переменные в области метода, и инициализируем их начальными данными. 
```kotlin
var low = 0
var high = list.size
```
Условием для нашего цикла будет то, что индекс начала не должен быть больше индекса конца массива.
В теле цикла сразу инициализируем счётчик, который поможет нам узнать, за сколько шаров мы нашли необходимый элемент.
Далее мы инициализируем опорный элемент, который находится по середине массива. После проверяем значение среднего элемента 
со значением необходимого и если они совпадают, то возвращаем его и останавливаем счётчик операций.
```kotlin
val mid = (low + high) / 2
val guard = list.getOrNull(mid) ?: break
if (guard == item) {
    endAndPrintCount("binary")
    return mid
}
```
endAndPrintCount ставим тег "binary", просто чтоб в консоли мы распознали текущий счётчик.
В случае если значения элементов не совпадают, мы проверяем в какой области массива находится искомый элемент,
и заменяем границы поиска.
```kotlin
if (guard > item) {
    high = mid - 1
} else {
    low = mid + 1
}
```
После, цикл возобновляется.

## Практика линейного поиска
В метод linealSearchingItemsIndex передаём значение необходимого элемента.
После создаем цикл, и идём по всем индексам массива. В теле инициализируем счётчик.
```kotlin
fun linealSearchingItemsIndex(item: Int): Int {
    for (i in list.indices) {
        startCount()
        .....
    }
```
По индексу достаём значение текущего элемента и сравниваем со значением искомого элемента, если равенство выполняется 
то останавливаем счётчик, ставим тег для логирования счётчика и возвращаем текущий индекс элемента. Если условие равенства не выполнится,
вернем -1, что будет означать о том, что искомого значения в массива нет.
```kotlin
if (list[i] == item) {
    endAndPrintCount("lineal")
    return i
}
```
## Метод find
Одна из фишек, это сахар для работы с коллекциями, который позволяет сокращать код и делает его более понятным.
Можно упростить код при линейном поиске, например:
```kotlin
fun linealSearchingItemsIndex(item: Int): Int = list.find { it == item } ?: -1
```
Метод find возвращает первый элемент по предикату или null, если такого элемента нет в массиве. 
И тут нам на помощь приходит elvis оператор, который вернёт -1. 
Под капотом происходит вызов метода firstOrNull  
```kotlin
@kotlin.internal.InlineOnly
public inline fun <T> Iterable<T>.find(predicate: (T) -> Boolean): T? {
    return firstOrNull(predicate)
}
```
Который в свою очередь создает обычный линейный поиск и поиск элемента по предикату и возвращает первый найденный элемент.
```kotlin
public inline fun <T> Iterable<T>.firstOrNull(predicate: (T) -> Boolean): T? {
    for (element in this) if (predicate(element)) return element
    return null
}
```

## Метод forEachIndexed
Хотелось бы еще рассказать про этот метод, он позволяет сразу итеритовать элементы массива и получать текущий индекс.
Поэтому наш метод линейного поиска можно переписать, например:
```kotlin
fun linealSearchingItemsIndex1(item: Int): Int {
    list.forEachIndexed { index, i -> if (i == item) return index }
    return -1
}
```
