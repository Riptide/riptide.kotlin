package org.codetome.riptide.messagebus.api

import org.assertj.core.api.Assertions.assertThat
import org.codetome.riptide.messagebus.domain.Message
import org.junit.Test
import java.util.concurrent.atomic.AtomicReference

@Suppress("UNUSED_PARAMETER")
class ConsumerBuilderTest {

    @Test
    fun shouldBuildDescriptorWithMinimalConfigurationWhenBuildIsCalled() {
        val value = "value"
        val param = AtomicReference<String>()
        val consumerFn: (String) -> Unit = {
            param.set(it)
        }
        val od = ConsumerBuilder()
                .withFunction(consumerFn)
                .build()

        od.consumerFn.invoke(Message.create(value))

        assertThat(param.get()).isEqualTo(value)
    }
}
