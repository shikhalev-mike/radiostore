package com.company.radiostore.view.productinstore;

import com.company.radiostore.entity.ProductInStore;
import com.company.radiostore.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "productInStores/:id", layout = MainView.class)
@ViewController("ProductInStore.detail")
@ViewDescriptor("product-in-store-detail-view.xml")
@EditedEntityContainer("productInStoreDc")
public class ProductInStoreDetailView extends StandardDetailView<ProductInStore> {
}