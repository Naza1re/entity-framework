package com.kotlin.entityframework.service

import com.kotlin.entityframework.dto.entity.request.CreateRequest
import com.kotlin.entityframework.dto.entity.request.UpdateRequest
import com.kotlin.entityframework.dto.entity.response.EntityResponse
import com.kotlin.entityframework.dto.entity.search.request.QlSearchRequest
import com.kotlin.entityframework.dto.entity.search.request.SearchRequest
import com.kotlin.entityframework.model.entity.Entity

interface EntityService {
    /*
     * Get entity by number
    */
    fun getByNumber(number: String): EntityResponse

    /*
    * Search entity my ml search('attribute' = '123' and 'attribute2' = 'support')
    */
    fun search(qlSearchRequest: QlSearchRequest): List<EntityResponse>

    /*
    * Create entity by entity create request
    */
    fun createEntity(createRequest: CreateRequest): EntityResponse

    /*
    * Delete entity by number
    */
    fun deleteByNumber(number: String)

    /*
    * Update entity by entity update request
    */
    fun updateEntity(number: String, updateRequest: UpdateRequest): EntityResponse

    /*
    * Update entity by properties throw the Map<String,Any>
     */
    fun updateEntity(entity: Entity, properties: Map<String, Any>)

    /*
    * Find entities by keyword with pagination
    */
    fun getEntities(searchRequest: SearchRequest): List<EntityResponse>
}
