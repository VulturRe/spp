package com.ilshatgalimovf.spp.domain

import javax.persistence.*

@Entity
@Table(name = "project")
internal data class Project(
        @Id
        @SequenceGenerator(name = "project_id", sequenceName = "project_id_seq", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id")
        var id: Long? = null,

        @Column (nullable = false) var name: String = "",

        @OneToOne(fetch = FetchType.EAGER, cascade = [(CascadeType.PERSIST), (CascadeType.REMOVE), (CascadeType.REFRESH)])
        @JoinColumn(name = "sheet_id")
        var sheet: Sheet? = null,

        @OneToMany(fetch = FetchType.EAGER, cascade = [(CascadeType.PERSIST), (CascadeType.REMOVE), (CascadeType.REFRESH)])
        @JoinColumn(name = "project_id", referencedColumnName = "id")
        var blank: MutableList<Blank>? = null
)
