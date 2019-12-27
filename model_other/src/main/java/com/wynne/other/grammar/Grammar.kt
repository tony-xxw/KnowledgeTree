package com.wynne.other.grammar

data class Grammar( val name: String?) {
    override fun equals(other: Any?): Boolean {
        if (other is Grammar) return other.name == name
        return false
    }
}