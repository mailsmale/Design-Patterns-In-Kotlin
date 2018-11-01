package adapter

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Volt(var volts: Int)
abstract class Socket120Volts {
    var volts: Volt = Volt(120)
}

interface Adapter {
    fun get120Volt(): Volt
    fun get3Volt(): Volt
    fun get12Volt(): Volt
}

class SocketClassAdapterImpl : Socket120Volts(), Adapter {
    override fun get3Volt(): Volt = convertVolts(volts, 40)
    override fun get12Volt(): Volt = convertVolts(volts, 10)
    override fun get120Volt(): Volt = volts
    private fun convertVolts(volts: Volt, devide: Int): Volt = Volt(volts.volts / devide)
}

private val LOGGER = logger<Volt>()
fun main(args: Array<String>) {
    println(SocketClassAdapterImpl().get3Volt().volts)
    println(SocketClassAdapterImpl().get12Volt().volts)
    println(SocketClassAdapterImpl().get120Volt().volts)
}

inline fun <reified T> logger(): Logger = LoggerFactory.getLogger(T::class.java)