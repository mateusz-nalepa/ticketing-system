package com.nalepa.ticketing.reporting.infra

import jakarta.annotation.PostConstruct
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

@Repository
class MongoReportRepository(
    private val mongoTemplate: MongoTemplate,
) {

}


@Component
class MongoStarter(
    private val mongoTemplate: MongoTemplate,
) {

    @PostConstruct
    fun initMongo() {
        mongoTemplate
            .findById("test", ReportEntity::class.java)
            ?: run {
                ReportEntity("test").let { mongoTemplate.save(it) }
            }
    }
}


@Document("reports")
data class ReportEntity(
    @Id
    var id: String,
)