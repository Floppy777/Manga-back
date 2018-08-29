package manga.controller

import manga.exception.MangaNotFoundException
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
        return try {
            ResponseEntity.ok(mangaService.getAllMangas())
        }catch (e: MangaNotFoundException){
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }catch (e: Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    /**
     * Get specified manga
     * @return 200 Ok
     * @return 404 Not Found
     * @return 500 Internal Error
     */
    @GetMapping("{id}")
    fun getMangaById(@PathVariable("id") id: Int) : ResponseEntity<Manga> {
        return try {
            ResponseEntity.ok(mangaService.getMangaById(id))
        }catch (e: MangaNotFoundException){
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }catch (e: Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    /**
     * Create a manga in database and return created instance
     * @return 201 Manga instance created
     * @return 500 Internal Server Error
     */
    @PostMapping
    fun createManga(manga:Manga): ResponseEntity<Manga> {
        return try {
            ResponseEntity.status(HttpStatus.CREATED).body(mangaService.persistManga(manga))
        }catch(e: Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    /**
     * Update a manga specified id
     * @return 200 Ok
     * @return 404 Specified manga not found
     * @return 500 internal error
     */
    @PutMapping("{id}")
    fun updateManga(@PathVariable("id") id: Int, @RequestBody manga: Manga) : ResponseEntity<Manga>{
        return try {
            ResponseEntity.ok(mangaService.updateManga(id,manga))
        }catch (e: MangaNotFoundException){
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }catch (e: Exception){
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }
}