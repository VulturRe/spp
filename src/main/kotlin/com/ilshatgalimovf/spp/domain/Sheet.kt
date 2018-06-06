package com.ilshatgalimovf.spp.domain

import javax.persistence.*

@Entity
@Table(name = "sheet")
internal open class Sheet(
        @Id
        @SequenceGenerator(name = "sheet_id", sequenceName = "sheet_id_seq", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sheet_id")
        val id: Long? = null,

        @Column(nullable = false) val length: Int = 1,
        @Column(nullable = false) val width: Int = 1,
        @Column(nullable = false) val count: Int = 1,

        @OneToOne(fetch = FetchType.EAGER, cascade = [(CascadeType.ALL)])
        @JoinColumn(name = "project_id")
        val project: Project? = null
)
