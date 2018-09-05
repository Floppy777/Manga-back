package manga.controller

import manga.model.Manga
import manga.service.MangaService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/mangas")
class MangaController(private val mangaService: MangaService) {

    /**
     * Get all mangas in database
     * @return 200 Ok
     * @return 404 List empty
     * @return 500 Internal Error
     */
    @GetMapping
    fun getAllMangas() : ResponseEntity<Iterable<Manga>> {
        return ResponseEntity.ok(mangaService.getAllMangas())
    }

    /**
     * Get specified manga
     * @return 200 Ok
     * @return 404 Not Found
     * @return 500 Internal Error
     */
    @GetMapping("{id}")
    fun getMangaById(@PathVariable("id") id: Int) : ResponseEntity<Manga> {
        return ResponseEntity.ok(mangaService.getMangaById(id))
    }

    /**
     * Create a manga in database and return created instance
     * @return 201 Manga instance created
     * @return 500 Internal Server Error
     */
    @PostMapping
    fun createManga(manga:Manga): ResponseEntity<Manga> {
        return ResponseEntity.status(HttpStatus.CREATED).body(mangaService.persistManga(manga))
    }

    /**
     * Update a manga specified id
     * @return 200 Ok
     * @return 404 Specified manga not found
     * @return 500 internal error
     */
    @PutMapping("{id}")
    fun updateManga(@PathVariable("id") id: Int, @RequestBody manga: Manga) : ResponseEntity<Manga>{
        return ResponseEntity.ok(mangaService.updateManga(id,manga))
    }
}