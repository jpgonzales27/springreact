package com.ventas.ventas.service;

import com.ventas.ventas.model.Cliente;
import com.ventas.ventas.repository.IClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository iClienteRepo;

    @Override
    public List<Cliente> findAll() {
        return iClienteRepo.findAll();
    }

    @Override
    public Optional<Cliente> findById(Integer id) {
        return iClienteRepo.findById(id);
    }

    @Override
    public Cliente create(Cliente cliente) {
        return iClienteRepo.save(cliente);
    }

    @Override
    public Cliente update(Cliente cliente) {
        return iClienteRepo.save(cliente);
    }

    @Override
    public void delete(Integer id) {
        iClienteRepo.deleteById(id);
    }
}
