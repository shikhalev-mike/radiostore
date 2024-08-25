package com.company.radiostore.view.product;

import com.company.radiostore.entity.Product;
import com.company.radiostore.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "products", layout = MainView.class)
@ViewController("Product.list")
@ViewDescriptor("product-list-view.xml")
@LookupComponent("productsDataGrid")
@DialogMode(width = "64em")
public class ProductListView extends StandardListView<Product> {
}