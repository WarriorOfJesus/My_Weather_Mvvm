package com.example.myweather.main_page.ui

class KelvinConverter {
    fun convert(value: Int?): String {
        var num: Int? = value?.minus(273)
//        if (num != null) num = (num * 100).toInt().div(100)
        var string: String = num.toString()
        string = "$string°C"
        return string
    }

    fun metersiNKilometrs(value: Int?): String {
        var num2 = value?.div(1000)
        var result:String = num2.toString()
        result = "${result} км"
        return result

    }

}