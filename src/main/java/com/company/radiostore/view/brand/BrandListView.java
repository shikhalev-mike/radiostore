package com.company.radiostore.view.brand;

import com.company.radiostore.entity.Brand;
import com.company.radiostore.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "brands", layout = MainView.class)
@ViewController("Brand.list")
@ViewDescriptor("brand-list-view.xml")
@LookupComponent("brandsDataGrid")
@DialogMode(width = "64em")
public class BrandListView extends StandardListView<Brand> {
}