package com.ilshatgalimovf.spp.domain

import javax.persistence.*

@Entity
@Table(name = "project")
internal open class Project(
        @Id
        @SequenceGenerator(name = "project_id", sequenceName = "project_id_seq", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_id")
        val id: Long? = null,

        @Column (nullable = false) val name: String = "",

        @OneToOne(mappedBy = "project", fetch = FetchType.EAGER, cascade = [(CascadeType.ALL)])
        val sheet: Sheet? = null,

        @OneToMany(mappedBy = "project", fetch = FetchType.EAGER, cascade = [(CascadeType.ALL)])
        val blank: List<Blank>? = null
) {
    override fun toString(): String {
        return name
    }
}
