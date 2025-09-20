package com.liveforpresent.onetapkiosk.common.core.infrastructure.util

object SnowflakeIdUtil {
    private const val EPOCH = 1680000000000L
    private const val DATACENTER_ID_BITS = 5
    private const val WORKER_ID_BITS = 5
    private const val SEQUENCE_BITS = 12

    // private const val MAX_DATACENTER_ID = (1L shl DATACENTER_ID_BITS) - 1
    // private const val MAX_WORKER_ID = (1L shl WORKER_ID_BITS) - 1
    private const val MAX_SEQUENCE = (1L shl SEQUENCE_BITS) - 1

    private val datacenterId: Long = 1
    private val workerId: Long = 1
    private var lastTimestamp = -1L
    private var sequence = 0L

    @Synchronized
    fun generateId(): Long {
        var currentTimestamp = currentTime()

        if (currentTimestamp < lastTimestamp) {
            throw IllegalStateException("현재 시간이 마지막 timestamp 보다 이전에 있습니다")
        }

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) and MAX_SEQUENCE
            if (sequence == 0L) currentTimestamp = waitNextMillis(currentTimestamp)
        } else {
            sequence = 0
        }

        lastTimestamp = currentTimestamp

        return ((currentTimestamp - EPOCH) shl (DATACENTER_ID_BITS + WORKER_ID_BITS + SEQUENCE_BITS)) or
                (datacenterId shl (WORKER_ID_BITS + SEQUENCE_BITS)) or
                (workerId shl SEQUENCE_BITS) or
                sequence
    }

    private fun waitNextMillis(currentTimestamp: Long): Long {
        var ts = currentTime()
        while (ts <= currentTimestamp) {
            ts = currentTime()
        }
        return ts
    }

    private fun currentTime(): Long = System.currentTimeMillis()
}
