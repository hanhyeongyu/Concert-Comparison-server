package kata.practice.concertcomparison.service

import kata.practice.concertcomparison.model.Poster
import org.springframework.web.multipart.MultipartFile

interface PosterService{
    fun save(concertId: Long, image: MultipartFile)
    fun delete(concertId: Long): Boolean

    fun poster(concertId: Long): Poster?

    fun url(concertId: Long): String?
}