package com.company.dao;

import com.company.model.Contract;

public interface ContractDao extends CRUDDao<Contract> {
    public void create(Contract contract);

    public Contract read(Long id);

    public void update(Contract contract);

    public void delete(Contract contract);
}