package com.parinya.backend.business;

import com.parinya.backend.exception.BaseException;
import com.parinya.backend.exception.ProductException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductBusiness {

    public String getProductById(String id) throws BaseException {

        if (Objects.equals("1234", id)) {
            throw ProductException.notFound();
        }

        return id;
    }
}
