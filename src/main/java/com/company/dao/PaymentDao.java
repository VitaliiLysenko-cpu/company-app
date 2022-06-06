package com.company.dao;

import com.company.model.Payment;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentDao extends CRUDDao<Payment> {
   public List<Payment> findAllByContract(Long contractId);
   public List<Payment> findAllByCustomer(Long customerId);
   public List<Payment> findAllAmountMoreThan(BigDecimal amount);
}
