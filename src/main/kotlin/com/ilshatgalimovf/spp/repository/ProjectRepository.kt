package com.ilshatgalimovf.spp.repository

import com.ilshatgalimovf.spp.domain.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal interface ProjectRepository : JpaRepository<Project, Long>
