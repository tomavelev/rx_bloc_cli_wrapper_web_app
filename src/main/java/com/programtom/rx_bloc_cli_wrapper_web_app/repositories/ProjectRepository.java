package com.programtom.rx_bloc_cli_wrapper_web_app.repositories;


import com.programtom.rx_bloc_cli_wrapper_web_app.models.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, String> {
    Page<Project> findAllByUserId(Pageable pageable, String userId);

    void deleteAllByIdAndUserId(String id, String userId);

    Page<Project> findAllByUserIdAndProjectNameLike(Pageable pageable, String userId, String projectName);

}

