package kata.practice.concertcomparison.application.command

import org.springframework.web.multipart.MultipartFile

data class UpdatePoster(
    val concertId: Long,
    val image: MultipartFile
)