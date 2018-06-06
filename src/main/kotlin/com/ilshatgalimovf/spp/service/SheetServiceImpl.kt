package com.ilshatgalimovf.spp.service

import com.ilshatgalimovf.spp.domain.Sheet
import com.ilshatgalimovf.spp.repository.SheetRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
internal class SheetServiceImpl @Autowired constructor(private val sheetRepository: SheetRepository) : SheetService {

    override fun save(sheet: Sheet): Sheet {
        return sheetRepository.save(sheet)
    }
}