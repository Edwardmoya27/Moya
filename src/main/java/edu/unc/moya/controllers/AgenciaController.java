package edu.unc.moya.controllers;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.unc.moya.domain.Agencia;
import edu.unc.moya.service.AgenciaService;

@RestController
@RequestMapping("api/v1/agencias")
public class AgenciaController {
	
		@Autowired
		private AgenciaService AgenciaService;
		
		@GetMapping
		public List<Agencia> ListarAgencia(){
			return AgenciaService.listarAgencia();
		}
		@GetMapping("/{id}")
		public ResponseEntity<?> listarPorAgencia(@PathVariable Long id){
			Optional<Agencia> AgenciaOptional = AgenciaService.buscarPorIdAgencia(id);
			if(AgenciaOptional.isPresent()) {
				return ResponseEntity.ok(AgenciaOptional.get());
			}
			return ResponseEntity.notFound().build();
		}
		@PostMapping
		public ResponseEntity<?> crearAgencia(@RequestBody Agencia agencia){
			return ResponseEntity.status(HttpStatus.CREATED).body(AgenciaService.grabarAgencia(agencia));
			
		}
		
		@PutMapping("/{id}")
		public ResponseEntity<?> editarAgencia(@PathVariable Long id,@RequestBody Agencia agencia){
			Optional<Agencia> o = AgenciaService.buscarPorIdAgencia(id);
			if(o.isPresent()) {
				Agencia AgenciaDB = o.get();
				AgenciaDB.setNombre(agencia.getNombre());
				AgenciaDB.setUbicacion(agencia.getUbicacion());
				return ResponseEntity.status(HttpStatus.CREATED).body(AgenciaService.grabarAgencia(AgenciaDB));
			}
			return ResponseEntity.notFound().build(); 
		}
		
		@DeleteMapping ("/{id}")
		public ResponseEntity<?> eliminarDpto(@PathVariable Long id){
			Optional<Agencia> o = AgenciaService.buscarPorIdAgencia(id);
			if(o.isPresent()) {
				AgenciaService.eliminarAgencia(id);
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.notFound().build(); 
		}

	@PostMapping("/{idAgencia}/garajes/{idGaraje}")
	public ResponseEntity<?> agregarGarajeAAgencia(@PathVariable Long idAgencia, @PathVariable Long idGaraje) {
		try {
			AgenciaService.agregarGarajeAAgencia(idAgencia, idGaraje);
			return ResponseEntity.ok().body(Map.of("status", "ok", "message", "Garaje agregado exitosamente a la agencia."));
		} catch (NoSuchElementException e) {
			return ResponseEntity.ok().body(Map.of("status", "error", "message", "Ha ocurrido un error al agregar el garaje"));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al agregar el garaje a la agencia.");
		}
	}
	
}
