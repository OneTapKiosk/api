package com.liveforpresent.onetapkiosk.user.company.command.domain

import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.CompanyId
import com.liveforpresent.onetapkiosk.user.company.command.domain.vo.RegistrationNumber
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.Instant

class CompanyTest: DescribeSpec({
    describe("Company") {
        val companyId = CompanyId(1L)
        val registrationNumber = RegistrationNumber.create("1234567890")
        val now = Instant.now()

        val companyProps = CompanyProps(
            registrationNumber = registrationNumber,
            phone = "1234567890",
            email = "aaaa@aaaaa.com",
            createdAt = now,
            updatedAt = now,
            isDeleted = false,
            deletedAt = null
        )

        describe("create") {
            val company = Company.create(companyId, companyProps)

            company.id shouldBe companyId
            company.registrationNumber shouldBe registrationNumber
            company.email shouldBe companyProps.email
            company.phone shouldBe companyProps.phone
            company.createdAt shouldBe now
            company.isDeleted shouldBe false
            company.deletedAt shouldBe null
        }
    }
})
