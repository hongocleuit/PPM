package io.agileintelligent.ppmtool.services;

import io.agileintelligent.ppmtool.domain.Project;
import io.agileintelligent.ppmtool.repositories.ProjectRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
@RequiredArgsConstructor
public class ProjectService {

    @NonNull private ProjectRepository projectRepository;
    @NonNull @Qualifier("jdbcScheduler") private Scheduler jdbcScheduler;
    @NonNull private TransactionTemplate transactionTemplate;

    public Mono<Project> saveOrUpdateProject(Project project) {
        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
                    return projectRepository.save(project);
                }))
                .subscribeOn(jdbcScheduler);
    }

    public Flux<Project> getAllProjects() {
        return Flux.defer(() -> Flux.fromIterable(projectRepository.findAll()))
                .subscribeOn(jdbcScheduler);
    }

    public Mono<Integer> deleteProject(Long projectId) {
        return Mono.fromCallable(() -> transactionTemplate.execute(status -> {
            projectRepository.deleteById(projectId);
            return 0;
        })).subscribeOn(jdbcScheduler);
    }
}
