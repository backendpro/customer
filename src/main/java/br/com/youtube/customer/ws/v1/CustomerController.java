package br.com.youtube.customer.ws.v1;

import br.com.youtube.customer.model.request.CustomerRequest;
import br.com.youtube.customer.model.response.CustomerResponse;
import br.com.youtube.customer.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @ApiOperation("API responsável por criar os clientes")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cria um cliente"),
            @ApiResponse(code = 500, message = "Foi gerado um erro ao criar o cliente")
    })
    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody CustomerRequest customerRequest) {
        LOGGER.info("Requisição recebida");
        return ResponseEntity.ok(customerService.create(customerRequest));
    }

    @ApiOperation("API responsável por listar os clientes")
    @GetMapping
    public ResponseEntity<Page<CustomerResponse>> getAll(Pageable pageable) {
        LOGGER.info("Buscando os registros");
        Page<CustomerResponse> customerResponses = customerService.getAll(pageable);
        return ResponseEntity.ok(customerResponses);
    }

    @ApiOperation("API responsável por atualizar os clientes")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable("id") Long id, @RequestBody CustomerRequest customerRequest) {
        LOGGER.info("Iniciando a atualização");
        Optional<CustomerResponse> update = customerService.update(id, customerRequest);
        if (!update.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(update.get());
    }

    @ApiOperation("API responsável por buscar um único cliente")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> get(@PathVariable("id") Long id) {
        LOGGER.info("Iniciando a busca pelo registro");
        Optional<CustomerResponse> customerResponse = customerService.get(id);
        if (!customerResponse.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerResponse.get());
    }

    @ApiOperation("API responsável por remover um único cliente por vez")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        LOGGER.info("Iniciando a remoção do registro");
        if (customerService.delete(id)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
