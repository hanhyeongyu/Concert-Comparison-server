package kata.practice.concertcomparison.member

class MemberRequests {

    data class SignupRequest(
        val email: String,
        val password: String
    )

    data class IssueTokenRequest(
        val email: String,
        val password: String
    )

    data class RefreshToken(
        val refreshToken: String
    )
}