package view;

import interface_adapter.location.LocationViewManagerModel;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewManager implements PropertyChangeListener {
    private final CardLayout cardLayout;
    private final JPanel views;
    private final LocationViewManagerModel viewManagerModel;

    public ViewManager(JPanel views, CardLayout cardLayout, LocationViewManagerModel viewManagerModel) {
        this.cardLayout = cardLayout;
        this.views = views;
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            cardLayout.show(views, (String) evt.getNewValue());
        }
    }
}
