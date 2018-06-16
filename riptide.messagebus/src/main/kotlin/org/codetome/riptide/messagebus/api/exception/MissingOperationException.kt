package org.codetome.riptide.messagebus.api.exception

import java.lang.RuntimeException

class MissingOperationException(override val message: String) : RuntimeException()
