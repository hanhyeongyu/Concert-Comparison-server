package kata.practice.concertcomparison.jwt

class IssueToken(
    val tokenType: String,
    val accessToken: String,
    val expiresIn: Long,
    val refreshToken: String,
    val refreshExpiresIn: Long
)