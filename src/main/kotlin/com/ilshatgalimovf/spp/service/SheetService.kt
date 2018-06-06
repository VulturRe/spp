package com.ilshatgalimovf.spp.service

import com.ilshatgalimovf.spp.domain.Sheet

internal interface SheetService {
    fun save(sheet: Sheet): Sheet
}