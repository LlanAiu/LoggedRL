package org.llan.loggedrl.logviewer.ui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public class Navigation {
    private MenuBar _menuBar;
    private Menu[] _menus;
    private FileSelector _fileSelector;

    public Navigation() {
        _menuBar = new MenuBar();
        _menus = new Menu[1];

        _menus[0] = new Menu("File");

        _menuBar.getMenus().addAll(_menus);
    }

    public void addSelector(Stage stage){
        _fileSelector = new FileSelector(stage);
        _menus[0].getItems().add(_fileSelector.getMenuItem());
    }

    public MenuBar getMenuBar() {
        return _menuBar;
    }
}
