package com.programtom.rx_bloc_cli_wrapper_web_app.repositories;

import com.programtom.rx_bloc_cli_wrapper_web_app.models.auth.Login;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoginRepository extends CrudRepository<Login, String> {

    List<Login> findByEmail(String email);
}
