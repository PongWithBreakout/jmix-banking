package com.company.jmixbanking.app;

import com.company.jmixbanking.entity.Account;
import com.company.jmixbanking.entity.OperationType;
import io.jmix.core.DataManager;
import io.jmix.core.entity.KeyValueEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class SpendingService {
    @Autowired
    private DataManager dataManager;

    public List<KeyValueEntity> getSpendingsByCategory( // Parameters
                                                        Account account, Date fromDate, Date toDate) {
        return dataManager.loadValues("select o.category, sum(o.amount) " +
                "from Operation o " +
                "where o.type = :type and o.account = :account " +
                                "and o.date between :from and :to group by o.category"
                )
                .properties("category", "amount")
                .parameter("type", OperationType.OPERATION_WITHDRAW)
                .parameter("account", account)
                .parameter("from", fromDate)
                .parameter("to", toDate).list();
    }
}