package io.agileintelligent.ppmtool.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
@Slf4j
public class ProjectRouter {

    @Bean
    public RouterFunction<ServerResponse> projectRoute(ProjectHandler projectHandler) {
        return RouterFunctions
                .route(GET("api/projects").and(accept(MediaType.APPLICATION_JSON)), projectHandler::getAllProjects)
                .andRoute(POST("api/project").and(accept(MediaType.APPLICATION_JSON)), projectHandler::createProject);
    }
}
