package com.company.radiostore.view.pricehistory;

import com.company.radiostore.entity.PriceHistory;
import com.company.radiostore.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "priceHistories", layout = MainView.class)
@ViewController("PriceHistory.list")
@ViewDescriptor("price-history-list-view.xml")
@LookupComponent("priceHistoriesDataGrid")
@DialogMode(width = "64em")
public class PriceHistoryListView extends StandardListView<PriceHistory> {
}