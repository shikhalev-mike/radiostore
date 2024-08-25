package com.company.radiostore.view.category;

import com.company.radiostore.entity.Category;
import com.company.radiostore.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "categories", layout = MainView.class)
@ViewController("Category.list")
@ViewDescriptor("category-list-view.xml")
@LookupComponent("categoriesDataGrid")
@DialogMode(width = "64em")
public class CategoryListView extends StandardListView<Category> {
}