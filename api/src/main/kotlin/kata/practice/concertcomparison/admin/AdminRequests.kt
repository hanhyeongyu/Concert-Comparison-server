package kata.practice.concertcomparison.admin

class AdminRequests {

    data class IssueTokenRequest(
        val email: String,
        val password: String
    )

    data class RefreshToken(
        val refreshToken: String
    )
}

