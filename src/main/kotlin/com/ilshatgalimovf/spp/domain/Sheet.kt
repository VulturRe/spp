package com.ilshatgalimovf.spp.domain

import org.springframework.lang.NonNull
import javax.persistence.*

@Entity
@Table(name = "sheet")
internal data class Sheet(
        @Id
        @SequenceGenerator(name = "sheet_id", sequenceName = "sheet_id_seq", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sheet_id")
        var id: Long? = null,

        @Column(nullable = false) var length: Int = 1,
        @Column(nullable = false) var width: Int = 1,
        @Column(nullable = false) var count: Int = 1/*,

        @OneToOne(fetch = FetchType.EAGER, cascade = [(CascadeType.PERSIST), (CascadeType.REMOVE), (CascadeType.REFRESH)])
        @JoinColumn(name = "project_id", nullable = false)
        @NonNull
        var project: Project? = null*/
)
