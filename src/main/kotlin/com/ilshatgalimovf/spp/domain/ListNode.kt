package com.ilshatgalimovf.spp.domain

data class ListNode(
        val id: Long,
        val name: String,
        val length: Int,
        val width: Int?,
        val count: Int,
        val type: ListNodeType
)
