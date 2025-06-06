package br.com.darthlyra.dlsec.srv.login.controller;

import br.com.darthlyra.dlsec.srv.login.domain.Funcionario;
import br.com.darthlyra.dlsec.srv.login.dto.DadosAutenticacao;
import br.com.darthlyra.dlsec.srv.login.dto.DadosSenha;
import br.com.darthlyra.dlsec.srv.login.dto.DadosTokenFuncionario;
import br.com.darthlyra.dlsec.srv.login.service.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/dlsec-srv-login")
public class LoginController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/v1/token-funcionario")
    public ResponseEntity tokenFuncionario(@RequestBody @Valid DadosAutenticacao dados) {
        var authorizationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authorizationToken);

        var tokenJWT = tokenService.gerarToken((Funcionario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenFuncionario(tokenJWT));
    }

    @PostMapping("/v1/encriptar")
    public void encriptarSenha(@RequestBody DadosSenha senha) {
        String salt = BCrypt.gensalt();  // Gera um salt aleatório
        String hashSenha = BCrypt.hashpw(senha.senha(), salt);  // Criptografa a senha

        System.out.println("Hash da senha: " + hashSenha);

    }
}
