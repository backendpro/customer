package br.com.youtube.customer.service;

import br.com.youtube.customer.model.request.CustomerRequest;
import br.com.youtube.customer.model.response.CustomerResponse;
import br.com.youtube.customer.persistence.entity.Customer;
import br.com.youtube.customer.persistence.repository.CustomerRepository;
import br.com.youtube.customer.service.mapper.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.util.Assert.notNull;

@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private Mapper<CustomerRequest, Customer> requestMapper;

    @Autowired
    private Mapper<Customer, CustomerResponse> responseMapper;

    @Override
    public CustomerResponse create(CustomerRequest customerRequest) {
        LOGGER.info("Criando um registro do cliente");
        notNull(customerRequest, "Request Inválida");
        Customer customer = this.requestMapper.map(customerRequest);
        return customerRepository.saveAndFlush(customer).map((Customer input) -> this.responseMapper.map(input));
    }

    @Override
    public Page<CustomerResponse> getAll(Pageable pageable) {
        LOGGER.info("Buscando todos os registros");
        notNull(pageable, "Página inválida");
        return customerRepository.findAll(pageable).map(customer -> this.responseMapper.map(customer));
    }

    @Override
    public Optional<CustomerResponse> update(Long id, CustomerRequest customerRequest) {
        LOGGER.info("Atualizando o registro");
        notNull(id, "ID Inválido");

        Customer customerUpdate = this.requestMapper.map(customerRequest);

        return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(customerUpdate.getName());
                    return this.responseMapper.map(customerRepository.saveAndFlush(customer));
                });
    }

    @Override
    public Optional<CustomerResponse> get(Long id) {
        LOGGER.info("Buscando registro");
        notNull(id, "ID Inválido");
        return customerRepository.findById(id).map(this.responseMapper::map);
    }

    @Override
    public boolean delete(Long id) {
        LOGGER.info("Removendo registro");
        notNull(id, "ID inválido");

        try{
            customerRepository.deleteById(id);
            return true;
        }catch (Exception e){
            LOGGER.warn("Erro ao remover o registro {}", id);
        }

        return false;
    }

}
