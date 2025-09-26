package com.liveforpresent.onetapkiosk.user.kiosk.command.presentation

import com.liveforpresent.onetapkiosk.user.kiosk.command.application.command.CreateKioskCommand
import com.liveforpresent.onetapkiosk.user.kiosk.command.application.command.DeleteKioskCommand
import com.liveforpresent.onetapkiosk.user.kiosk.command.application.command.UpdateKioskCommand
import com.liveforpresent.onetapkiosk.user.kiosk.command.application.handler.CreateKioskHandler
import com.liveforpresent.onetapkiosk.user.kiosk.command.application.handler.DeleteKioskHandler
import com.liveforpresent.onetapkiosk.user.kiosk.command.application.handler.UpdateKioskHandler
import com.liveforpresent.onetapkiosk.user.kiosk.command.presentation.dto.CreateKioskReqDto
import com.liveforpresent.onetapkiosk.user.kiosk.command.presentation.dto.UpdateKioskReqDto
import com.liveforpresent.onetapkiosk.common.core.presentation.BaseApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/kiosk")
class KioskCommandController(
    private val createKioskHandler: CreateKioskHandler,
    private val updateKioskHandler: UpdateKioskHandler,
    private val deleteKioskHandler: DeleteKioskHandler,
) {
    @PostMapping
    fun createKiosk(@RequestBody reqDto: CreateKioskReqDto): ResponseEntity<BaseApiResponse<Unit>> {
        val command = CreateKioskCommand(
            name = reqDto.name,
            location = reqDto.location,
            status = reqDto.status,
            version = reqDto.version,
            devices = reqDto.devices,
            companyId = reqDto.companyId,
        )

        createKioskHandler.execute(command)

        val response = BaseApiResponse<Unit>(
            success = true,
            message = "키오스크 생성 성공"
        )

        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PatchMapping("{kioskId}")
    fun updateKiosk(@PathVariable kioskId: Long, @RequestBody reqDto: UpdateKioskReqDto): ResponseEntity<BaseApiResponse<Unit>> {
        val command = UpdateKioskCommand(
            kioskId = kioskId,
            name = reqDto.name,
            location = reqDto.location,
            status = reqDto.status,
            version = reqDto.version,
            devices = reqDto.devices,
            companyId = reqDto.companyId,
        )

        updateKioskHandler.execute(command)

        val response = BaseApiResponse<Unit>(
            success = true,
            message = "키오스크 정보 수정 성공"
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @DeleteMapping("{kioskId}")
    fun deleteKiosk(@PathVariable kioskId: Long): ResponseEntity<BaseApiResponse<Unit>> {
        val command = DeleteKioskCommand(kioskId)

        deleteKioskHandler.execute(command)

        val response = BaseApiResponse<Unit>(
            success = true,
            message = "키오스크 삭제 성공"
        )

        return ResponseEntity.status(HttpStatus.OK).body(response)
    }
}
