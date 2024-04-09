package edu.unc.moya.controllers;

import edu.unc.moya.domain.Cliente;
import edu.unc.moya.domain.Reserva;
import edu.unc.moya.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarCliente();
    }

	@GetMapping("/{id}/reservas")
	public ResponseEntity<?> obtenerReservasDeCliente(@PathVariable Long id) {
		try {
			Optional<Cliente> clienteOptional = clienteService.buscarPorIdCliente(id);
			if (clienteOptional.isEmpty()) {
				return ResponseEntity.status(403).body(Map.of("status", "error", "message", "No existe el cliente"));
			}
			Cliente cliente = clienteOptional.get();
			List<Reserva> reservas = cliente.getReservasClienteReservante();
			return ResponseEntity.ok(reservas);
		} catch (Exception e) {
			return ResponseEntity.status(500).body(Map.of("status", "error", "message", "Ha ocurrido un error en el servidor"));
		}
	}


	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorIdCliente(@PathVariable Long id) {
		try {
			Optional<Cliente> clienteOptional = clienteService.buscarPorIdCliente(id);
			if(clienteOptional.isEmpty())
				return ResponseEntity.status(403).body(Map.of("status", "error", "message", "No existe el cliente"));
			return ResponseEntity.ok().body(clienteOptional);
		}catch (Exception e){
			return ResponseEntity.status(500).body(Map.of("status", "error", "message", "Ha ocurrido un error en el servidor"));
		}
	}
    @PostMapping
    public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.grabarCliente(cliente));
    }

	@PutMapping("/{id}")
	public ResponseEntity<?> editarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
		Optional<Cliente> o = clienteService.buscarPorIdCliente(id);
		if (o.isPresent()) {
			Cliente clienteDB = o.get();

			if (cliente.getDni() != null) {
				clienteDB.setDni(cliente.getDni());
			}
			if (cliente.getNombre() != null) {
				clienteDB.setNombre(cliente.getNombre());
			}
			if (cliente.getDireccion() != null) {
				clienteDB.setDireccion(cliente.getDireccion());
			}
			if (cliente.getTelefono() != null) {
				clienteDB.setTelefono(cliente.getTelefono());
			}
			return ResponseEntity.ok(clienteService.grabarCliente(clienteDB));
		}
		return ResponseEntity.ok().body(Map.of("status", "error", "message", "No se ha encontrado el ID del cliente"));
	}


	@DeleteMapping ("/{id}")
	public ResponseEntity<?> eliminarCliente(@PathVariable Long id){
		Optional<Cliente> o = clienteService.buscarPorIdCliente(id);
		if(o.isPresent()) {
			clienteService.eliminarCliente(id);
			return ResponseEntity.ok().body(Map.of("status", "ok", "message", "Cliente eliminado correctamente"));
		}
		return ResponseEntity.ok().body(Map.of("status", "error", "message", "No se ha encontrado el ID del cliente"));
	}

}

