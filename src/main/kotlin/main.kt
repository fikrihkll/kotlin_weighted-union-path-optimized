/**
 * # Weighted-Union and Path Optimized
 * ## input
 *
 * (e.g. "a=4 5")
 *
 * a. union
 * b. is connected
 * c. print
 * d. exit
 *
 */
fun main() {
    println("how many n=?")
    println()
    val n = readLine()
    val uf = UF(n!!.toInt())
    println()
    println("input: ")
    while (input(uf))
        true
}

private fun input(uf: UF): Boolean {
    try {
        val input = readLine()
        val value = input?.split("=")
        if (input!![0] == 'a') {
            val valueSplit = value!![1].split(" ")
            uf.union(valueSplit[0].toInt(), valueSplit[1].toInt())
            print(uf)
        }
        else if (input[0] == 'b') {
            val valueSplit = value!![1].split(" ")
            println()
            println("connected(${valueSplit[0]}, ${valueSplit[1]}) = ${uf.connected(valueSplit[0].toInt(), valueSplit[1].toInt())}")
            println()
        }
        else if (input[0] == 'c') {
            print(uf)
        }
        else {
            return false
        }
    } catch (e: Exception) {
        println("try again")
        println()
        return true
    }

    return true
}

private fun print(uf: UF) {
    for(i in uf.id.indices)
        print("$i ")
    println()
    uf.id.forEach {
        print("$it ")
    }
    println()
    uf.sz.forEach {
        print("$it ")
    }
    println()
    println("------------")
    println()
}

class UF( n: Int) {
    // This UF has been weighted and path optimized

    var id: Array<Int> = Array(n) {
        it
    }
    var sz: Array<Int> = Array(n) {
        1
    }

    private fun root(x: Int): Int {
        var i = x
        while (i != id[i]) {
            // point to its grandparent
            id[i] = id[id[i]]
            // we find the root after we've pointed it to the grandparent
            i = id[i]
        }
        return i
    }

    fun connected(p: Int, q: Int): Boolean {
        return root(p) == root(q)
    }

    fun union(p: Int, q: Int) {
        val i = root(p)
        val j = root(q)
        if (i == j) return
        // the smaller one will join to the bigger one
        if (sz[i] < sz[j]) {
            id[i] = j // change i to be part of j children
            sz[j] += sz[i] // increase j children
        } else { // vice versa
            id[j] = i
            sz[i] += sz[j]
        }
    }

}