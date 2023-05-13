package com.example.kodehauz.common

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone


object Parse {
    fun parse(params: String): Date? {
        var input = params
        val def = SimpleDateFormat("yyy-MM-dd 'T' HH:mm:ssz")



        if (input.endsWith("z"))
            input = input.substring(0, input.length - 1) + "GMT-00:00"
        else {
            val insert = 6
            val s0 = input.substring(0, input.length - insert)
            val s1 = input.substring(input.length - insert, input.length)

            input = s0 + "GMT" + s1

        }
        return def.parse(input)
    }


    fun toString(date: Date): String {
        val def = SimpleDateFormat("yyy-MM-dd 'T' HH:mm:ssz")
        val txt = TimeZone.getTimeZone("UTC")

        def.timeZone = txt

        val output = def.format(date)
        val inset0 = 6
        val inset1 = 9
        val s0 = output.substring(0, output.length - inset0)
        val s1 = output.substring(output.length - inset1, output.length)


        var result = s0 + s1

        result = result.replace("UTC".toRegex(),"+00:000")


        return result
    }
}