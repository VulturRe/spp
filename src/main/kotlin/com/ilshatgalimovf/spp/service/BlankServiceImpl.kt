package com.ilshatgalimovf.spp.service

import com.ilshatgalimovf.spp.domain.Blank
import com.ilshatgalimovf.spp.repository.BlankRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
internal class BlankServiceImpl @Autowired constructor(private val blankRepository: BlankRepository) : BlankService {

    @Transactional
    override fun save(blank: Blank): Blank {
        return blankRepository.save(blank)
    }
}