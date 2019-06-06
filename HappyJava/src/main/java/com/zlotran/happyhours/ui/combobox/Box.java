package com.zlotran.happyhours.ui.combobox;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;

public abstract class Box<E> extends JComboBox<E> {

    abstract void setItems();

    abstract void action();

    public abstract void refresh();

    void removeArrow() {
        for (Component component : this.getComponents())
        {
            if (component instanceof JButton) {
                this.remove(component);
            }
        }
    }
}
