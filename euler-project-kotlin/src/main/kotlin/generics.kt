interface ReadableGroup<out T> {
    fun fetch(): T
}

interface WritableGroup<in T> {
    fun insert(item: T)
}

// interface Group<T> : ReadableGroup<T>, WritableGroup<T> {
// }

interface Group<T> {
    fun fetch(): T
    fun insert(item: T)
}

class GroupImpl<T>: Group<T> {
    override fun fetch(): T {
        TODO("Not yet implemented")
    }

    override fun insert(item: T) {
        TODO("Not yet implemented")
    }
}


fun read(group: Group<out Animal>): Animal {
    return group.fetch()
}

fun write(group: Group<in Animal>) {
    group.insert(Dog())
}

open class Animal

class Dog: Animal()

fun main() {
    val group = GroupImpl<Animal>()

    group.insert(Dog())
    group.insert(Animal())

    val fetched = group.fetch()
}