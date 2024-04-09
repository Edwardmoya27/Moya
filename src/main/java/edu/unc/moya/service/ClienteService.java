package edu.unc.moya.service;

import java.util.List;
import java.util.Optional;
import edu.unc.moya.domain.Cliente;

public interface ClienteService {
	List<Cliente>listarCliente();
	Optional<Cliente> buscarPorIdCliente(Long idCliente);
	Cliente grabarCliente(Cliente cliente);
	void eliminarCliente(long idCliente);
}
