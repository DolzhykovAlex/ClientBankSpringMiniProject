package app.entities.customers.service;

import app.entities.customers.api.DTO.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class CustomerPaginatorConverter {


    public static Page<CustomerResponse> convertListToPage(List<CustomerResponse> items, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<CustomerResponse> list;

        if (items.size() < startItem) {
            list = List.of();
        } else {
            int toIndex = Math.min(startItem + pageSize, items.size());
            list = items.subList(startItem, toIndex);
        }
        return new PageImpl<>(list, pageable, items.size());
    }
}


