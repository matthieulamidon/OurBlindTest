package fr.eseo.b3.mlbcpg.ourblindtest.data

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class OurBlindTestConverters {
    @TypeConverter
    fun fromTimeStamp(value : Long?) : LocalDateTime? {
        return value?.let{
            Instant.ofEpochSecond(value)
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
        }
    }

    @TypeConverter
    fun toTuimeStamp(value : LocalDateTime?) : Long? {
        return value?.let{
            value
                .atZone(ZoneId.systemDefault())
                .toEpochSecond()
        }
    }
}