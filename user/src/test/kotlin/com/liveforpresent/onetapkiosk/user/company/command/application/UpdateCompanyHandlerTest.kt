package com.liveforpresent.onetapkiosk.user.company.command.application

import com.liveforpresent.onetapkiosk.common.core.domain.DomainEventPublisher
import com.liveforpresent.onetapkiosk.common.core.domain.vo.identifiers.CompanyId
import com.liveforpresent.onetapkiosk.common.core.infrastructure.util.SnowflakeIdUtil
import com.liveforpresent.onetapkiosk.user.company.command.application.command.UpdateCompanyCommand
import com.liveforpresent.onetapkiosk.user.company.command.application.handler.UpdateCompanyHandler
import com.liveforpresent.onetapkiosk.user.company.command.domain.Company
import com.liveforpresent.onetapkiosk.user.company.command.domain.CompanyCommandRepository
import com.liveforpresent.onetapkiosk.user.company.command.domain.CompanyProps
import com.liveforpresent.onetapkiosk.user.company.command.domain.vo.RegistrationNumber
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.*
import java.time.Instant

class UpdateCompanyHandlerTest: DescribeSpec({
    lateinit var companyCommandRepository: CompanyCommandRepository
    lateinit var updateCompanyHandler: UpdateCompanyHandler
    lateinit var eventPublisher: DomainEventPublisher

    beforeEach {
        companyCommandRepository = mockk()
        eventPublisher = mockk()
        updateCompanyHandler = UpdateCompanyHandler(companyCommandRepository, eventPublisher)
    }
    mockkObject(SnowflakeIdUtil)

    describe("UpdateCompanyHandler") {
        it("모든 필드가 바뀌었을 때 수정에 성공해야 한다.") {
            val companyId = CompanyId(1L)
            val oldRegistrationNumber = RegistrationNumber.create("1234567890")
            val oldEmail = "old@example.com"
            val oldPhone = "111-222-3333"
            val createdAt = Instant.now()

            val newRegistrationNumber = "9876543210"
            val newEmail = "new@example.com"
            val newPhone = "444-555-6666"

            val existingCompany = Company.create(
                id = companyId, CompanyProps(
                    registrationNumber = oldRegistrationNumber,
                    email = oldEmail,
                    phone = oldPhone,
                    createdAt = createdAt,
                    updatedAt = createdAt,
                    isDeleted = false,
                    deletedAt = null,
                )
            )

            val command = UpdateCompanyCommand(
                id = companyId.value,
                newRegistrationNumber = newRegistrationNumber,
                newEmail = newEmail,
                newPhone = newPhone
            )

            every { companyCommandRepository.findOne(any<Long>()) } returns existingCompany
            val updatedCompanySlot = slot<Company>()
            every { companyCommandRepository.save(capture(updatedCompanySlot)) } answers { updatedCompanySlot.captured }
            every { eventPublisher.publish(any<Company>()) } just Runs

            updateCompanyHandler.execute(command)

            verify(exactly = 1) { companyCommandRepository.findOne(any<Long>())  }
            verify(exactly = 1) { companyCommandRepository.save(any<Company>()) }
            verify(exactly = 1) { eventPublisher.publish(any<Company>()) }

            updatedCompanySlot.captured shouldNotBe null
            updatedCompanySlot.captured.registrationNumber.value shouldBe newRegistrationNumber
            updatedCompanySlot.captured.email shouldBe newEmail
            updatedCompanySlot.captured.phone shouldBe newPhone
        }

        it("일부 필드가 바뀌었을 때 수정에 성공해야 한다.") {
            val companyId = CompanyId(1L)
            val oldRegistrationNumber = RegistrationNumber.create("1234567890")
            val oldEmail = "old@example.com"
            val oldPhone = "111-222-3333"
            val createdAt = Instant.now()

            val newRegistrationNumber = "9876543210"
            val newEmail = "new@example.com"

            val existingCompany = Company.create(
                id = companyId, CompanyProps(
                    registrationNumber = oldRegistrationNumber,
                    email = oldEmail,
                    phone = oldPhone,
                    createdAt = createdAt,
                    updatedAt = createdAt,
                    isDeleted = false,
                    deletedAt = null,
                )
            )

            val command = UpdateCompanyCommand(
                id = companyId.value,
                newRegistrationNumber = newRegistrationNumber,
                newEmail = newEmail,
                newPhone = oldPhone
            )

            every { companyCommandRepository.findOne(any<Long>()) } returns existingCompany
            val updatedCompanySlot = slot<Company>()
            every { companyCommandRepository.save(capture(updatedCompanySlot)) } answers { updatedCompanySlot.captured }
            every { eventPublisher.publish(any<Company>()) } just Runs

            updateCompanyHandler.execute(command)

            verify(exactly = 1) { companyCommandRepository.findOne(any<Long>())  }
            verify(exactly = 1) { companyCommandRepository.save(any<Company>()) }
            verify(exactly = 1) { eventPublisher.publish(any<Company>()) }

            updatedCompanySlot.captured shouldNotBe null
            updatedCompanySlot.captured.registrationNumber.value shouldBe newRegistrationNumber
            updatedCompanySlot.captured.email shouldBe newEmail
            updatedCompanySlot.captured.phone shouldBe oldPhone
        }
    }
})
