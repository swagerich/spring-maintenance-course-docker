package com.erich.hibernate.services.impl;

import com.erich.hibernate.entity.Empresa;
import com.erich.hibernate.repository.EmpresaRepository;
import com.erich.hibernate.services.EmpresaService;
import org.springframework.stereotype.Service;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    private final EmpresaRepository empresaRepo;

    public EmpresaServiceImpl(EmpresaRepository empresaRepository){
        this.empresaRepo = empresaRepository;
    }

    @Override
    public Empresa create(Empresa empresa) {
        return empresaRepo.save(empresa);
    }
}
