package com.cadastro.empresas.resource;

import com.cadastro.empresas.dto.EmpresaDto;
import com.cadastro.empresas.models.Empresa;
import com.cadastro.empresas.services.EmpresaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EmpresaResource {

    @Autowired
    EmpresaService empresaService;

    @ApiOperation(value = "Retorna uma lista de Empresas")
    @GetMapping("/listarEmpresas")
    public ResponseEntity<List<EmpresaDto>> listaEmpresa() {
        List<Empresa> empresas = empresaService.findAll();
        List<EmpresaDto> listDTO = empresas.stream().map(x -> new EmpresaDto(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @ApiOperation(value = "Retorna uma empresa unica")
    @GetMapping("/empresa/{id}")
    public ResponseEntity<EmpresaDto> listaEmpresaUnica(@PathVariable Long id) {
        Empresa empresa = empresaService.findById(id);
        return ResponseEntity.ok().body(new EmpresaDto(empresa));
    }

    @ApiOperation(value = "Salva uma empresa")
    @PostMapping("/salvaEmpresa")
    public ResponseEntity<EmpresaDto> save(@RequestBody EmpresaDto empresaDto) {
        Empresa empresa = empresaService.fromDTO(empresaDto);
        return ResponseEntity.ok().body(new EmpresaDto(empresaService.save(empresa)));
    }

    @ApiOperation(value = "Atualiza uma empresa")
    @PutMapping("/atualizaEmpresa/{id}")
    public ResponseEntity<EmpresaDto> update(@PathVariable Long id, @RequestBody EmpresaDto empresaDto) {
        Empresa empresa = empresaService.fromDTO(empresaDto);
        empresa.setId(id);
        return ResponseEntity.ok().body(new EmpresaDto(empresaService.update(empresa)));
    }

    @ApiOperation(value = "Deleta uma empresa")
    @DeleteMapping("/deletarEmpresa/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        empresaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
