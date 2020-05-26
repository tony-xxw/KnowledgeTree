package com.wynne.advanced.dagger

import javax.inject.Inject

 open class Noodle @Inject constructor() {

  override fun toString(): String {
   return "普通面条"
  }

}