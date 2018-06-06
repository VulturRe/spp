package com.ilshatgalimovf.spp.domain

import javax.persistence.*

@Entity
@Table(name = "blank")
internal data class Blank(
        @Id
        @SequenceGenerator(name = "blank_id", sequenceName = "blank_id_seq", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blank_id")
        var id: Long? = null,

        @Column(nullable = false) var length: Int = 1,
        @Column(nullable = false) var count: Int = 1,
        @Column(name = "project_id", nullable = false) var projectId: Long? = null
)
