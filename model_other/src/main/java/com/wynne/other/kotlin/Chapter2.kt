package com.wynne.other.kotlin

import android.util.Log
import kotlin.properties.Delegates

class Chapter2 {
    private val str = null;

    fun show() {
        //函数类型,高阶函数,Lambda表达式
        val countryApp = CountryApp()
        val countryTest = CountryTest()
        val countries = arrayListOf<Country>(Country("法国", "EU", 100))
        Log.d("XXW", "size: ${countryApp.filterCountries3(countries, countryTest::isBigEuropeanCountry).size}")
        //匿名函数
        countryApp.filterCountries3(countries, fun(country: Country): Boolean {
            return country.continent == "EU" && country.population < 100
        })

        //lambda表达式
        countryApp.filterCountries3(countries) { country -> country.continent == "EU" && country.population < 100 }

        //lambda表达式
        val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }

        val sum1 = { x: Int, y: Int -> x + y }

        val sum2: (Int, Int) -> Int = { x, y -> x + y }

        fun foo(int: Int) = {
            print(int)
        }

        listOf(1, 2, 3).forEach { foo(it).invoke() }

        val foo1 = { x: Int, y: Int -> x + y }


        Log.d("XXW", str ?: "11")
        Log.d("XXW", DayWeek.MON.day.toString())
    }

    fun chapter3() {
        var test by Delegates.notNull<Int>()
        fun doSomething() {
            test = 1
            Log.d("XXW", "test value is ${test}")
            test = 2
        }

        doSomething()

    }


    fun isBigEuropeanCountry(country: Country): Boolean {
        return country.continent == "EU" && country.population < 10000
    }

}


data class Country(val name: String, val continent: String, val population: Int)

class CountryApp {
    fun filterCountries(countries: List<Country>): List<Country> {
        val res = mutableListOf<Country>()
        for (country in countries) {
            if (country.continent == "EU") res.add(country)
        }
        return res
    }

    fun filterCountries1(countries: List<Country>, continent: String): List<Country> {
        val res = mutableListOf<Country>()
        for (country in countries) {
            if (country.continent == continent) res.add(country)
        }
        return res
    }

    fun filterCountries2(countries: List<Country>, continent: String, population: Int): List<Country> {
        val res = mutableListOf<Country>()
        for (country in countries) {
            if (country.continent == continent && country.population > population) res.add(country)
        }
        return res
    }

    fun filterCountries3(countries: List<Country>, test: (Country) -> Boolean): List<Country> {
        val res = mutableListOf<Country>()
        for (country in countries) {
            if (test(country)) res.add(country)
        }
        return res
    }
}

class CountryTest {
    fun isBigEuropeanCountry(country: Country): Boolean {
        return country.continent == "EU" && country.population > 10000
    }

}

enum class DayWeek(val day: Int) {
    MON(1);
}
