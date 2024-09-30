package kata.practice.concertcomparison.jwt


interface JwtRepository{
    fun add(id: String, refreshToken : String, refreshExpiration: Long)
    fun token(id: String): String?
}

