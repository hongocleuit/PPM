package io.agileintelligent.ppmtool.web;


import io.agileintelligent.ppmtool.domain.Project;
import io.agileintelligent.ppmtool.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public ResponseEntity<Flux<Project>> getAllProjects() {
        return ResponseEntity.ok().body(projectService.getAllProjects());
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Flux<Project>> getProjectByIdentification(@PathVariable String projectId) {
        return ResponseEntity.ok().body(projectService.getAllProjects());
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity deleteProjectByIdentification(@PathVariable Long projectId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(projectService.deleteProject(projectId));
    }

    @PostMapping
    public ResponseEntity<Mono<Project>> createProject(@RequestBody @Valid Project project) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.saveOrUpdateProject(project));
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Mono<Project>> updateProject(@RequestBody @Valid Project project) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.saveOrUpdateProject(project));
    }
}
