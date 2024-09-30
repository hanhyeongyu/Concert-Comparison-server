package kata.practice.concertcomparison.maps

import kata.practice.concertcomparison.maps.MapsResourcesController.Companion.ENDPOINT
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(ENDPOINT)
class MapsResourcesController(
    private val resourceLoader: ResourceLoader
){


    @GetMapping("/theater-screen")
    fun theaterScreen(): ResponseEntity<Resource>{
        val resource = resourceLoader.getResource("classpath:/static/map/img-theater-screen.png")
        return ResponseEntity
            .ok()
            .contentType(MediaType.IMAGE_PNG)
            .body(resource)
    }

    @GetMapping("/door-left")
    fun doorLeft(): ResponseEntity<Resource>{
        val resource = resourceLoader.getResource("classpath:/static/map/img-door-left.png")
        return ResponseEntity
            .ok()
            .contentType(MediaType.IMAGE_PNG)
            .body(resource)
    }

    @GetMapping("/door-right")
    fun doorRight(): ResponseEntity<Resource>{
        val resource = resourceLoader.getResource("classpath:/static/map/img-door-right.png")
        return ResponseEntity
            .ok()
            .contentType(MediaType.IMAGE_PNG)
            .body(resource)
    }


    companion object{
        internal const val ENDPOINT = "maps/resources"
    }
}