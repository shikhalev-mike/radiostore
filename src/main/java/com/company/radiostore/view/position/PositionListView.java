package com.company.radiostore.view.position;

import com.company.radiostore.entity.Position;
import com.company.radiostore.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;


@Route(value = "positions", layout = MainView.class)
@ViewController("Position_.list")
@ViewDescriptor("position-list-view.xml")
@LookupComponent("positionsDataGrid")
@DialogMode(width = "64em")
public class PositionListView extends StandardListView<Position> {
}