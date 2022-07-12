package com.company.jmixbanking.app;

import com.company.jmixbanking.entity.Account;
import com.company.jmixbanking.entity.Operation;
import io.jmix.core.DataManager;
import com.company.jmixbanking.entity.OperationCategory;
import com.company.jmixbanking.entity.OperationType;
import io.jmix.core.EntityValueAccessException;
import io.jmix.core.Messages;
import io.jmix.core.entity.KeyValueEntity;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public List<KeyValueEntity> getSpendingsByCategory() {
        return dataManager.loadValues("select o.category, sum(o.amount) " +
                        "from Operation o " +
                        "where o.type = :type group by o.category"
                )
                .properties("category", "amount")
                .parameter("type", OperationType.OPERATION_WITHDRAW).list();
    }


    @Autowired
    private Messages messages;
}