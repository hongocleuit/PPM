package io.agileintelligent.ppmtool.web;

import io.agileintelligent.ppmtool.domain.Project;
import io.agileintelligent.ppmtool.services.ProjectService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ProjectHandler {

    private ProjectService projectService;

    public ProjectHandler(ProjectService projectService) {
        this.projectService = projectService;
    }

    public Mono<ServerResponse> createProject(ServerRequest serverRequest) {
        Mono<Project> productToSave = serverRequest.bodyToMono(Project.class);
        return productToSave.flatMap(project ->
                ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(projectService.saveOrUpdateProject(project), Project.class));
    }

    public Mono<ServerResponse> getAllProjects(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(projectService.getAllProjects(), Project.class);
    }
}
