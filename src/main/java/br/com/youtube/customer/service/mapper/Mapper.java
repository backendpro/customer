package br.com.youtube.customer.service.mapper;

public interface Mapper<A, B> {
    B map(A input);
}
