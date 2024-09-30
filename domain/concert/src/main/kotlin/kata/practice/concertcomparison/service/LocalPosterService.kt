package kata.practice.concertcomparison.service

import jakarta.annotation.PostConstruct
import kata.practice.concertcomparison.model.Poster
import kata.practice.concertcomparison.connector.FileConnector
import kata.practice.concertcomparison.model.FileUtils
import kata.practice.concertcomparison.repository.PosterRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.io.path.Path
import kotlin.jvm.optionals.getOrNull

@Service
class LocalPosterService(
    @Value("\${file.images.poster}")
    private val dir: String,
    private val fileConnector: FileConnector,
    private val posterRepository: PosterRepository,
): PosterService{


    @PostConstruct
    private fun init(){
        val path = Path(dir)
        Files.createDirectories(path)
    }

    override fun save(concertId: Long, image: MultipartFile) {
        val fileName = FileUtils.createName(image)
        val path = Paths.get(dir, fileName)
        fileConnector.save(image, path)

        val poster = Poster(
            concertId = concertId,
            filePath = path.toString()
        )
        posterRepository.save(poster)
    }

    override fun delete(concertId: Long): Boolean {
        val poster = posterRepository.findByConcertId(concertId)
            .getOrNull() ?: return false
        posterRepository.delete(poster)

        val path = Paths.get(poster.filePath)
        return fileConnector.delete(path)
    }

    override fun poster(concertId: Long): Poster? {
        return posterRepository.findByConcertId(concertId)
            .getOrNull()
    }

    override fun url(concertId: Long): String?{
        val poster = posterRepository.findByConcertId(concertId)
            .getOrNull() ?: return null

        val path = Paths.get(poster.filePath)
        val result = fileConnector.url(path)
        return result
    }
}