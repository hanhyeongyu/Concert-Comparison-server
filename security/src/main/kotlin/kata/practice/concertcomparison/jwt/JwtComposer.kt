package kata.practice.concertcomparison.jwt



interface JwtComposer {
    fun compose(subject: String, roles: List<String>): IssueToken
    fun compose(refreshToken: String): IssueToken
}