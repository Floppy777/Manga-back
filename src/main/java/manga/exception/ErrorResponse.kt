package manga.exception


data class ErrorResponse(
        var errorCode: Int? = null,
        var message: String? = null
)