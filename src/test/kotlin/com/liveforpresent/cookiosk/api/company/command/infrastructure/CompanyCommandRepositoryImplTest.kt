package com.liveforpresent.cookiosk.api.company.command.infrastructure

import com.liveforpresent.cookiosk.api.company.command.domain.Company
import com.liveforpresent.cookiosk.api.company.command.domain.CompanyProps
import com.liveforpresent.cookiosk.api.company.command.domain.vo.CompanyId
import com.liveforpresent.cookiosk.api.company.command.domain.vo.RegistrationNumber
import com.liveforpresent.cookiosk.shared.exception.CustomException
import com.liveforpresent.cookiosk.shared.exception.CustomExceptionCode
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.ints.exactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.time.Instant
import java.util.Optional

class CompanyCommandRepositoryImplTest: DescribeSpec({
    val companyCommandJpaRepository = mockk<CompanyCommandJpaRepository>()
    val companyCommandRepositoryImpl = CompanyCommandRepositoryImpl(companyCommandJpaRepository)

    val companyId = CompanyId(1L)
    val registrationNumber = RegistrationNumber.create("1234567890")
    val now = Instant.now()
    val companyProps = CompanyProps(
        registrationNumber = registrationNumber,
        phone = "1234567890",
        email = "aaaaa@aaaaaaaa.com",
        createdAt = now,
        isDeleted = false,
        deletedAt = null
    )

    val company = Company.create(companyId, companyProps)
    val companyEntity = CompanyEntity.toPersistence(company)

    describe("save") {
        it("새로운 Company를 Company Entity로 변환 뒤 저장하고 Company를 return한다.") {
            every { companyCommandJpaRepository.save(any<CompanyEntity>()) } returns companyEntity

            val result = companyCommandRepositoryImpl.save(company)

            result shouldBe company

            verify(exactly = 1) { companyCommandJpaRepository.save(any<CompanyEntity>())  }
        }
    }

    describe("findOne") {
        context("id에 해당하는 company가 존재하는 경우") {
            it("company entity를 찾아 company로 변환 후 반환한다.") {
                every { companyCommandJpaRepository.findById(any<Long>()) } returns Optional.of(companyEntity)

                val result = companyCommandRepositoryImpl.findOne(companyEntity.id)

                result shouldBe company
            }
        }

        context("id에 해당하는 company가 존재하지 않는 경우") {
            it("Custom Exception 중 COMPANY_NOT_FOUND 예외를 던진다.") {
                every { companyCommandJpaRepository.findById(999L) } returns Optional.empty()

                val exception = shouldThrow<CustomException> {
                    companyCommandRepositoryImpl.findOne(999L)
                }

                exception.code shouldBe CustomExceptionCode.COMPANY_NOT_FOUND
                exception.code.message shouldContain "사업체는 존재하지 않습니다."
            }
        }
    }
})
