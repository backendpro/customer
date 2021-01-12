package br.com.youtube.customer.service.mapper;

import br.com.youtube.customer.model.request.CustomerRequest;
import br.com.youtube.customer.persistence.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerRequestMapper implements Mapper<CustomerRequest, Customer>{

    @Override
    public Customer map(CustomerRequest input) {
        if(input == null){
            return null;
        }

        Customer customer = new Customer();
        customer.setName(input.getName());
        customer.setDocument(input.getDocument());

        return customer;
    }
}
