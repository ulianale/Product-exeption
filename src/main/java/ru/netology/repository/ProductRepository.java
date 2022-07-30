package ru.netology.repository;

import ru.netology.domain.*;

public class ProductRepository {
    private Product[] products = new Product[0];

    public void save(Product product) {
        if (findById(product.getId()) != null) {
            throw new AlreadyExistsException(
                    "Товар : " + product + " уже добавлен"
            );
        }
        Product[] tmp = new Product[products.length + 1];
        for (int i = 0; i < products.length; i++) {
            tmp[i] = products[i];
        }
        tmp[tmp.length - 1] = product;
        products = tmp;
    }

    public Product findById(int id) {
        for (Product product : products ) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void removeById(int id) {

        if (findById(id) == null) {
            throw new NotFoundException(
                    "Товар с ID : " + id + " не найден"
            );
        }
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }

    public Product[] findAll() {
        return products;
    }

}
