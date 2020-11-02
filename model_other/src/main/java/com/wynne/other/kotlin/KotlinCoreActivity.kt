package com.wynne.other.kotlin

import android.util.Log
import com.wynne.knowledge.base.base.BaseActivity
import com.wynne.other.R

class KotlinCoreActivity : BaseActivity() {
    val num = intArrayOf(1, 2, 3)

    override fun initView() {
        chapter2()
    }


    fun chapter2() {
        //函数类型,高阶函数,Lambda表达式
        val countryApp = CountryApp()
        val countryTest = CountryTest()
        val countries = arrayListOf<Country>(Country("法国", "EU", 100))
        Log.d("XXW", "size: ${countryApp.filterCountries3(countries, countryTest::isBigEuropeanCountry).size}")
        //匿名函数
        countryApp.filterCountries3(countries, fun(country: Country): Boolean {
            return country.continent == "EU" && country.population < 100
        })

        countryApp.filterCountries3(countries) { country -> country.continent == "EU" && country.population < 100 }

        //lambda表达式
        val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }

        val sum1 = { x: Int, y: Int -> x + y }

        val sum2: (Int, Int) -> Int = { x, y -> x + y }

        fun foo(int: Int) = {
            print(int)
        }
        listOf(1, 2, 3).forEach { foo(it).invoke() }
    }


    override fun getLayoutId(): Int = R.layout.activity_kotlin_core_layout

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
