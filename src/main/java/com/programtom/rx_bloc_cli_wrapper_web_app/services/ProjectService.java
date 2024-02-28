package com.programtom.rx_bloc_cli_wrapper_web_app.services;

import com.programtom.rx_bloc_cli_wrapper_web_app.repositories.ProjectRepository;
import com.programtom.rx_bloc_cli_wrapper_web_app.models.QueryResult;
import com.programtom.rx_bloc_cli_wrapper_web_app.models.Project;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {


    @Autowired
    ProjectRepository projectRepository;

    public QueryResult<Project> selectByUserPage(String userId, long offset, long limit) {
        Page<Project> page = projectRepository.findAllByUserId(Pageable.ofSize((int) limit).withPage((int) (offset / limit)), userId);
        return new QueryResult<>(page.get().toList(), page.getTotalElements());
    }

     @Transactional
    public void deleteRecord(Project project, String userId) {
        projectRepository.deleteAllByIdAndUserId(project.getId(), userId);
    }

     @Transactional
    public void save(Project project) {
        projectRepository.save(project);
    }

    public QueryResult<Project> selectByUserAndSearchPage(String userId, String search, long offset, long limit) {
        if (search.isEmpty()) {
            return selectByUserPage(userId, offset, limit);
        }
        Page<Project> page = projectRepository.findAllByUserIdAndProjectNameLike(Pageable.ofSize((int) limit).withPage((int) (offset / limit)), userId, "%" + search + "%");
        return new QueryResult<>(page.getContent(), page.getTotalElements());
    }

}
