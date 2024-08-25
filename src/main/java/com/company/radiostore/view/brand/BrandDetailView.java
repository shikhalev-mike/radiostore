package com.company.radiostore.view.brand;

import com.company.radiostore.entity.Brand;
import com.company.radiostore.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "brands/:id", layout = MainView.class)
@ViewController("Brand.detail")
@ViewDescriptor("brand-detail-view.xml")
@EditedEntityContainer("brandDc")
public class BrandDetailView extends StandardDetailView<Brand> {
}