package org.codetome.riptide.messagebus.api.exception

import java.lang.RuntimeException

class MissingMessageReceiverException : RuntimeException("MessagingAdapter has no message receiver!")
