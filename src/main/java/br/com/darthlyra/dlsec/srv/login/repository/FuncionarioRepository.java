package br.com.darthlyra.dlsec.srv.login.repository;

import br.com.darthlyra.dlsec.srv.login.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    UserDetails findByLogin(String login);
}
