package com.company.jmixbanking.screen.operation;

import com.company.jmixbanking.entity.Account;
import com.company.jmixbanking.entity.Operation;
import com.company.jmixbanking.entity.OperationType;
import io.jmix.core.DataManager;
import io.jmix.core.EntityValueAccessException;
import io.jmix.core.entity.KeyValueEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OperationService {
    @Autowired
    private DataManager dataManager;

    public void changeAccountFunds(Operation newOperation) {
        Account account = newOperation.getAccount();
        Integer version = account.getVersion();
        OperationType type = newOperation.getType();
        if (version.equals(account.getVersion())) {
            if (type == OperationType.OPERATION_WITHDRAW)
                account.setFunds(account.getFunds().subtract(newOperation.getAmount()));
            else if (type == OperationType.OPERATION_DEPOSIT)
                account.setFunds(account.getFunds().add(newOperation.getAmount()));
        }
        else
            throw new EntityValueAccessException("Versioning failed");
    }

    public BigDecimal getAccountFunds(Account account)
    {
        BigDecimal funds = BigDecimal.ZERO;
        List<KeyValueEntity> amounts = dataManager.loadValues("select o.type, o.amount " +
                "from Operation o " +
                "where o.account = :account ")
                .properties("type", "sum")
                .parameter("account", account)
                .list();
        for (KeyValueEntity a : amounts) {
            if (OperationType.fromId(a.getValue("type")) == OperationType.OPERATION_DEPOSIT)
                funds = funds.add(a.getValue("sum"));
            else if (OperationType.fromId(a.getValue("type")) == OperationType.OPERATION_WITHDRAW)
                funds = funds.subtract(a.getValue("sum"));
        }
        return funds;
    }
}