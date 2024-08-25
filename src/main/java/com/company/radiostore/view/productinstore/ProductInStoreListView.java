package com.company.radiostore.view.productinstore;

import com.company.radiostore.entity.ProductInStore;
import com.company.radiostore.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "productInStores", layout = MainView.class)
@ViewController("ProductInStore.list")
@ViewDescriptor("product-in-store-list-view.xml")
@LookupComponent("productInStoresDataGrid")
@DialogMode(width = "64em")
public class ProductInStoreListView extends StandardListView<ProductInStore> {
}