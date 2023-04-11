package com.itpearls.agritomarketplace.screen.main;

import com.itpearls.agritomarketplace.AgritoGlobalValue;
import com.itpearls.agritomarketplace.entity.MyHousehold;
import com.itpearls.agritomarketplace.entity.ProductByer;
import com.itpearls.agritomarketplace.screen.myhousehold.MyHouseholdEdit;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.ScreenTools;
import io.jmix.ui.component.AppWorkArea;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.Window;
import io.jmix.ui.component.mainwindow.AppMenu;
import io.jmix.ui.component.mainwindow.Drawer;
import io.jmix.ui.component.mainwindow.SideMenu;
import io.jmix.ui.icon.JmixIcon;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("MainScreen")
@UiDescriptor("main-screen.xml")
@Route(path = "main", root = true)
public class MainScreen extends Screen implements Window.HasWorkArea {

    @Autowired
    private ScreenTools screenTools;

    @Autowired
    private AppWorkArea workArea;
    @Autowired
    private Drawer drawer;
    @Autowired
    private Button collapseDrawerButton;
    @Autowired
    private ScreenBuilders screenBuilders;
    @Autowired
    private SideMenu sideMenu;
    @Autowired
    private MessageBundle messageBundle;


    @Override
    public AppWorkArea getWorkArea() {
        return workArea;
    }

    @Subscribe("collapseDrawerButton")
    private void onCollapseDrawerButtonClick(Button.ClickEvent event) {
        drawer.toggle();
        if (drawer.isCollapsed()) {
            collapseDrawerButton.setIconFromSet(JmixIcon.CHEVRON_RIGHT);
        } else {
            collapseDrawerButton.setIconFromSet(JmixIcon.CHEVRON_LEFT);
        }
    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        UiControllerUtils.getScreenContext(this).getScreens();
        screenTools.handleRedirect();

        selectMyHousehold();
    }

    private void selectMyHousehold() {
        screenBuilders.lookup(MyHousehold.class, this)
                .withOpenMode(OpenMode.DIALOG)
                .withSelectHandler(myHouseholds -> {
                    AgritoGlobalValue.counterparty = myHouseholds.iterator().next();
                    AgritoGlobalValue.myHousehold = (MyHousehold) AgritoGlobalValue.counterparty;

                    SideMenu.MenuItem myHouseholdSideMenuItem = sideMenu.createMenuItem("edit-my-household",
                            AgritoGlobalValue.counterparty.getCounterpartyName(),
                            null,
                            menuItem -> {
                                screenBuilders.editor(MyHousehold.class, this)
                                        .withScreenClass(MyHouseholdEdit.class)
                                        .editEntity(AgritoGlobalValue.myHousehold)
                                        .build()
                                        .show();
                            });
                    myHouseholdSideMenuItem.setDescription(messageBundle.getMessage("msgEditMyHousehold")
                            + " \""
                            + AgritoGlobalValue.myHousehold.getCounterpartyName()
                            + "\"");

                    sideMenu.addMenuItem(myHouseholdSideMenuItem, 0);

                })
                .build()
                .show();
    }
}
