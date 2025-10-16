package com.nalepa.ticketing.ticketing.infra

import org.springframework.stereotype.Repository

@Repository
class MongoReportRepository(
    private val mongoTemplate: MongoTemplate
) {
}