package com.company.radiostore.view.position;

import com.company.radiostore.entity.Position;
import com.company.radiostore.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.EditedEntityContainer;
import io.jmix.flowui.view.StandardDetailView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "positions/:id", layout = MainView.class)
@ViewController("Position_.detail")
@ViewDescriptor("position-detail-view.xml")
@EditedEntityContainer("positionDc")
public class PositionDetailView extends StandardDetailView<Position> {
}