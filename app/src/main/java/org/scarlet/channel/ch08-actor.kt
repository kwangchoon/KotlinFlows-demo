@file:OptIn(ObsoleteCoroutinesApi::class)

package org.scarlet.channel

import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.scarlet.util.log

/**
 * This starts an actor that runs on the default dispatcher and records
 * every message sent to it.
 */

object ActorDemo {
    @JvmStatic
    fun main(args: Array<String>) = runBlocking<Unit> {

        val printActor: SendChannel<String> = actor/*(Dispatchers.Default)*/ {
            val messages = mutableListOf<String>()

            for (msg in channel) {
                messages.add(msg)
            }

            log(messages.joinToString(", "))
        }

        repeat(10) { i ->
            printActor.send(i.toString())
            log("$i sent")
            delay(1000)
            if (i == 1) {
                printActor.close()
            }
        }

        printActor.close()
    }
}