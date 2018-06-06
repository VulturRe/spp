package com.ilshatgalimovf.spp.service

import com.ilshatgalimovf.spp.domain.Project
import com.ilshatgalimovf.spp.repository.ProjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
internal class ProjectServiceImpl @Autowired constructor(private val projectRepository: ProjectRepository) : ProjectService {

    override fun findAll(): List<Project> {
        return projectRepository.findAll()
    }
}
