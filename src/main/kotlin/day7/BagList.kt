package day7

class BagList(private val bags: List<Bag>) {

    fun residesInBags(name: String) : Int {
        var result = 0

        for (bag in bags) {
            if (bag.containsBag(name)) {
                result += 1
            }
        }
        return result
    }

    fun countBagsInside(name: String) : Int {
        return bags.filter { it.name == name }.first().countBagsInside()
    }
}

data class Bag(val name: String) {
    private var contains = mutableMapOf<Bag, Int>()

    fun addBag(bag: Bag, amount: Int) {
        contains[bag] = amount
    }

    fun containsBag(name: String): Boolean {
        var result = false
        for (bag in contains.keys) {
            if (bag.name == name || bag.containsBag(name)) {
                return true
            }
        }
        return result
    }

    fun countBagsInside() : Int {
        var result = 0
        for (bag in contains) {
            result += bag.value
            result += (bag.value * bag.key.countBagsInside())
        }
        return result
    }
}