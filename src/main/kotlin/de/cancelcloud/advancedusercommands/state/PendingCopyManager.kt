package de.cancelcloud.advancedusercommands.state

data class PendingCopy(
    val content: String,
    val timestamp: Long = System.currentTimeMillis()
)

object PendingCopyManager {
    private var pending: PendingCopy? = null

    fun setPending(content: String) {
        pending = PendingCopy(content)
    }

    fun getPending(): PendingCopy? = pending

    fun hasPending(): Boolean = pending != null

    fun getAgeSeconds(): Long {
        val current = pending ?: return Long.MAX_VALUE
        return (System.currentTimeMillis() - current.timestamp) / 1000
    }

    fun clear() {
        pending = null
    }
}
