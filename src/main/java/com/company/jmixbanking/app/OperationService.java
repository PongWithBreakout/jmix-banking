package com.company.jmixbanking.app;

import io.jmix.core.DataManager;
import com.company.jmixbanking.entity.OperationCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OperationService {
    @Autowired
    private DataManager dataManager;

    public Map<OperationCategory, BigDecimal> getSpendingsByCategory() {
        Map<OperationCategory, BigDecimal> spendings = dataManager.loadValues(
                "select category, amount" +
                        "from Operation" +
                        "group by category order by amount"
        ).properties("category", "amount").list().stream()
               // .map(x -> x.<OperationCategory>getValue("category"))
                .collect(Collectors.toMap(x -> x.<OperationCategory>getValue("category"),
                        y -> y.<BigDecimal>getValue("amount")));
        return spendings;
    }

}