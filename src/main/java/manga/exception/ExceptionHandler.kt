package manga.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(MangakaNotFoundException::class)
    fun MangakaNotFoundHandler(ex: MangakaNotFoundException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.errorMessage)
        return ResponseEntity(error, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MangaNotFoundException::class)
    fun MangaNotFoundHander(ex: MangaNotFoundException): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.message)
        return ResponseEntity(error,HttpStatus.NOT_FOUND)
    }

}