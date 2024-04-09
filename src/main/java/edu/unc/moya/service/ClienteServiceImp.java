package edu.unc.moya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.unc.moya.domain.Cliente;
import edu.unc.moya.repositories.ClienteRepository;
@Service
public class ClienteServiceImp implements ClienteService {
	@Autowired
	private ClienteRepository ClienteRep;

	@Override
	public List<Cliente> listarCliente() {
		return (List<Cliente>)ClienteRep.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Cliente> buscarPorIdCliente(Long idCliente) {
		
		return ClienteRep.findById(idCliente);
	}

	@Override
	public Cliente grabarCliente(Cliente cliente) {
		return ClienteRep.save(cliente);
	}

	@Override
	public void eliminarCliente(long idCliente) {
		ClienteRep.deleteById(idCliente);
		
	}

}
