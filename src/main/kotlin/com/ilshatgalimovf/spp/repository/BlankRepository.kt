package com.ilshatgalimovf.spp.repository

import com.ilshatgalimovf.spp.domain.Blank
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal interface BlankRepository : JpaRepository<Blank, Long>
