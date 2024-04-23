import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

val logger: Logger = LoggerFactory.getLogger("request-interceptor")

@Component
class LoggerInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        logger.info("[REQUEST] ${request.method} ${request.requestURI}")
        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        val responseData = response.outputStream.flush()
        logger.info("[RESPONSE] ${request.method} ${request.requestURI} ${response.status} ${response.contentType} ${responseData}")
    }
}

@Configuration
class WebMvcConfig(private val customHandlerInterceptor: LoggerInterceptor) : WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(customHandlerInterceptor)
    }
}