package org.lessons.pizzeria.webapi.pizzeria_webapi.service;

import java.util.List;
import java.util.Optional;

import org.lessons.pizzeria.webapi.pizzeria_webapi.exception.IdNotFoundException;
import org.lessons.pizzeria.webapi.pizzeria_webapi.model.OnSale;
import org.lessons.pizzeria.webapi.pizzeria_webapi.repository.OnSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class OnSaleService {

    @Autowired
    private OnSaleRepository saleRepository;

    // index
    public List<OnSale> findAll() {
        return saleRepository.findAll();
    }

    // show
    public OnSale getById(int id) {
        Optional<OnSale> saleAttempt = saleRepository.findById(id);

        if (saleAttempt.isEmpty()) {
            throw new IdNotFoundException("404: Sale with id " + id + " not found.");
        }

        return saleAttempt.get();
    }

    // ricerche personalizzate
    public List<OnSale> findSortedByStartDate() {
        return saleRepository.findAll(Sort.by("startDate"));
    }
    public List<OnSale> findSortedByFinishDate() {
        return saleRepository.findAll(Sort.by("finishDate"));
    }

    // create
    public OnSale create(OnSale formSale) {
        return saleRepository.save(formSale);
    }

    // update
    public OnSale update(OnSale formSale) {
        return saleRepository.save(formSale);
    }

    // delete
    public void deleteById(int id) {
        saleRepository.deleteById(id);
    }

    public void delete(OnSale sale) {
        deleteById(sale.getId());
    }
}
