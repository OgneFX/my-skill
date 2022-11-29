class Stack<T> {

    val stack = mutableListOf<T>()

    fun push(item: T) {
        stack.add(stack.count(), item)
    }

    fun pop(): T? {
        if (stack.isNotEmpty())
            return stack.removeAt(stack.count() - 1)
        else return null
    }

    fun isEmpty(): Boolean {
        return stack.isNotEmpty()
    }

}