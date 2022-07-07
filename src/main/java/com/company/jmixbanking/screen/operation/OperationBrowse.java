package com.company.jmixbanking.screen.operation;

import io.jmix.ui.screen.*;
import com.company.jmixbanking.entity.Operation;

@UiController("Operation.browse")
@UiDescriptor("operation-browse.xml")
@LookupComponent("operationsTable")
public class OperationBrowse extends StandardLookup<Operation> {
}