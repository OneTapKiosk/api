package com.liveforpresent.cookiosk.api.company.command.application

import com.liveforpresent.cookiosk.api.company.command.application.command.CreateCompanyCommand
import com.liveforpresent.cookiosk.api.company.command.application.handler.CreateCompanyHandler
import com.liveforpresent.cookiosk.api.company.command.domain.Company
import com.liveforpresent.cookiosk.api.company.command.domain.CompanyCommandRepository
import com.liveforpresent.cookiosk.api.company.command.domain.CompanyProps
import com.liveforpresent.cookiosk.api.company.command.domain.vo.CompanyId
import com.liveforpresent.cookiosk.api.company.command.domain.vo.RegistrationNumber
import com.liveforpresent.cookiosk.shared.core.domain.DomainEventPublisher
import com.liveforpresent.cookiosk.shared.core.infrastructure.util.SnowflakeIdUtil
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.*
import java.time.Instant

class CreateCompanyHandlerTest: DescribeSpec({
    val companyCommandRepository = mockk<CompanyCommandRepository>()
    val eventPublisher = mockk<DomainEventPublisher>()
    val createCompanyHandler = CreateCompanyHandler(companyCommandRepository, eventPublisher)
    mockkObject(SnowflakeIdUtil)

    describe("CreateCompanyHandler") {
        val registrationNumberString = "1234567890"
        val command = CreateCompanyCommand(
            registrationNumber = registrationNumberString,
            phone = "1234567890",
            email = "test@example.com",
        )

        it("Company 도메인 객체를 생성 후, DB에 저장한다.") {
            every { companyCommandRepository.findByRegistrationNumber(command.registrationNumber) } returns null
            val capturedCompany = slot<Company>()
            every { companyCommandRepository.save(capture(capturedCompany)) } answers { capturedCompany.captured }
            every { eventPublisher.publish(any<Company>()) } just Runs

            val result = createCompanyHandler.execute(command)

            verify(exactly = 1) { companyCommandRepository.save(any<Company>()) }
            verify(exactly = 1) { eventPublisher.publish(any<Company>()) }

            val createdCompany = capturedCompany.captured

            createdCompany.id.value shouldBe result.companyId.toLong()
            createdCompany.registrationNumber.value shouldBe command.registrationNumber
            createdCompany.phone shouldBe command.phone
            createdCompany.email shouldBe command.email
            createdCompany.isDeleted shouldBe false
            createdCompany.deletedAt shouldBe null
        }

        it("CreateCompanyResDto를 반환한다.") {
            val companyId = CompanyId(1L)

            every { SnowflakeIdUtil.generateId() } returns companyId.value
            every { companyCommandRepository.save(any<Company>()) } returns Company.create(companyId, CompanyProps(
                registrationNumber = RegistrationNumber.create(command.registrationNumber),
                phone = command.phone,
                email = command.email,
                createdAt = Instant.now(),
                updatedAt = Instant.now(),
                isDeleted = false,
                deletedAt = null
            ))

            val result = createCompanyHandler.execute(command)

            result.companyId.toLong() shouldBe companyId.value
        }
    }
})
