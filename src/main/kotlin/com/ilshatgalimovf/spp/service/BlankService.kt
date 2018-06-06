package com.ilshatgalimovf.spp.service

import com.ilshatgalimovf.spp.domain.Blank

internal interface BlankService {

    fun save(blank: Blank): Blank
}