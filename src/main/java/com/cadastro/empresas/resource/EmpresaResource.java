package com.cadastro.empresas.resource;

import com.cadastro.empresas.models.Empresa;
import com.cadastro.empresas.repository.EmpresaRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmpresaResource {

    @Autowired
    EmpresaRepository empresaRepository;

    @ApiOperation(value = "Retorna uma lista de Empresas")
    @GetMapping("/empresas")
    public List<Empresa> listaEmpresa() {
        return empresaRepository.findAll();
    }

    @ApiOperation(value = "Retorna uma empresa unica")
    @GetMapping("/empresa/{id}")
    public Empresa listaProdutoUnico(@PathVariable(value = "id") long id) {
        return empresaRepository.findById(id);
    }

    @ApiOperation(value = "Salva uma empresa")
    @PostMapping("/empresa")
    public Empresa salvaProduto(@RequestBody @Valid Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @ApiOperation(value = "Deleta uma empresa")
    @DeleteMapping("/empresa")
    public void deletaProduto(@RequestBody @Valid Empresa empresa) {
        empresaRepository.delete(empresa);
    }

    @ApiOperation(value = "Atualiza uma empresa")
    @PutMapping("/empresa")
    public Empresa atualizaProduto(@RequestBody @Valid Empresa empresa) {
        return empresaRepository.save(empresa);
    }
}
