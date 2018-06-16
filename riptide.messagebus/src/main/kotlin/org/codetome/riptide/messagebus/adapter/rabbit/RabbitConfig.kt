package org.codetome.riptide.messagebus.adapter.rabbit

data class RabbitConfig(val host: String = "localhost",
                        val port: Int = 5672)
