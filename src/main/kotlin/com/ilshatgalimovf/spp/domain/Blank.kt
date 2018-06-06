package com.ilshatgalimovf.spp.domain

import javax.persistence.*

@Entity
@Table(name = "blank")
internal open class Blank(
        @Id
        @SequenceGenerator(name = "blank_id", sequenceName = "blank_id_seq", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blank_id")
        val id: Long? = null,

        @Column(nullable = false) val length: Int = 1,
        @Column(nullable = false) val count: Int = 1,

        @ManyToOne(fetch = FetchType.EAGER, cascade = [(CascadeType.ALL)])
        @JoinColumn(name = "project_id")
        val project: Project? = null
)
