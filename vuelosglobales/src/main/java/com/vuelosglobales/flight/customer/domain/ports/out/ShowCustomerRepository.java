package com.vuelosglobales.flight.customer.domain.ports.out;

import java.util.List;

public interface ShowCustomerRepository {
    List<String> getCustomerList(String id);
}
