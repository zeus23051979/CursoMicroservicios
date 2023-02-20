package com.academy.digitallab.shopping.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academy.digitallab.shopping.models.entity.InvoiceItem;

@Repository
public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem,Long> {
}
