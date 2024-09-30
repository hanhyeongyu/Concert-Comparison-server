package kata.practice.concertcomparison.admin

import kata.practice.concertcomparison.admin.AdminIssueController.Companion.ENDPOINT
import kata.practice.concertcomparison.codable.PasswordEncodable
import kata.practice.concertcomparison.common.exception.BaseException
import kata.practice.concertcomparison.common.exception.EntityNotFoundException
import kata.practice.concertcomparison.common.exception.ErrorCode.INVALID_PASSWORD
import kata.practice.concertcomparison.jwt.IssueToken
import kata.practice.concertcomparison.jwt.JwtService
import kata.practice.concertcomparison.user.UserReadModel
import kata.practice.concertcomparison.user.model.UserModel
import kata.practice.concertcomparison.user.query.GetUserByEmail
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(ENDPOINT)
class AdminIssueController(
    private val jwtService: JwtService,
    private val passwordEncodable: PasswordEncodable,
    private val userReadModel: UserReadModel,
){

    @PostMapping()
    fun issue(@RequestBody request: AdminRequests.IssueTokenRequest): ResponseEntity<IssueToken> {
        val getUserByEmail = GetUserByEmail(request.email)
        val member = userReadModel.user(getUserByEmail) ?: throw EntityNotFoundException()
        if (isMatch(request, member)){
            val token = jwtService.issueToken(member.id.toString(), listOf("ADMIN"))
            return ResponseEntity.ok(token)
        }else{
            throw BaseException(INVALID_PASSWORD)
        }
    }


    @PostMapping("/refresh")
    fun issue(@RequestBody request: AdminRequests.RefreshToken): ResponseEntity<IssueToken> {
        val token = jwtService.issueToken(request.refreshToken)
        return ResponseEntity.ok(token)
    }

    private fun isMatch(
        request: AdminRequests.IssueTokenRequest,
        member: UserModel
    ): Boolean{
        return passwordEncodable.matches(request.password, member.encodedPassword)
    }


    companion object{
        internal const val ENDPOINT = "admin/issue"
    }
}