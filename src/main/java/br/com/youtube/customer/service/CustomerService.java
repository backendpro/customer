package br.com.youtube.customer.service;

import br.com.youtube.customer.model.request.CustomerRequest;
import br.com.youtube.customer.model.response.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CustomerService {

    CustomerResponse create(CustomerRequest customerRequest);

    Page<CustomerResponse> getAll(Pageable pageable);

    Optional<CustomerResponse> update(Long id, CustomerRequest customerRequest);

    Optional<CustomerResponse> get(Long id);

    boolean delete(Long id);
}
