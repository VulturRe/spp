package com.ilshatgalimovf.spp.service

import com.ilshatgalimovf.spp.domain.Project


internal interface ProjectService {

    fun findAll(): List<Project>
}