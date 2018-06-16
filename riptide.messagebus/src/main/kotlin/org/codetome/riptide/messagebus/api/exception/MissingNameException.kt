package org.codetome.riptide.messagebus.api.exception

import java.lang.RuntimeException

class MissingNameException : RuntimeException("Can't publish message without an operation name specified!")
