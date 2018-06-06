package com.ilshatgalimovf.spp.repository

import com.ilshatgalimovf.spp.domain.Sheet
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal interface SheetRepository : JpaRepository<Sheet, Long>
