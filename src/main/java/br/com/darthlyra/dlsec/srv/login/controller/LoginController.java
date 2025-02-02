package br.com.darthlyra.dlsec.srv.login.controller;

import br.com.darthlyra.dlsec.srv.login.dto.DadosAutenticacao;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dlsec-srv-login")
public class LoginController {

    @PostMapping("/v1/token-funcionario")
    public ResponseEntity tokenFuncionario(@RequestBody @Valid DadosAutenticacao dados) {

        return ResponseEntity.ok(dados);
    }
}
