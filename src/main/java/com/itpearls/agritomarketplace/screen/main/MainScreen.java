package com.itpearls.agritomarketplace.screen.main;

import com.itpearls.agritomarketplace.AgritoGlobalValue;
import com.itpearls.agritomarketplace.entity.*;
import com.itpearls.agritomarketplace.screen.myhousehold.MyHouseholdEdit;
import com.itpearls.agritomarketplace.screen.myhousehold.SelectMyHouseholdBrowse;
import com.itpearls.agritomarketplace.screen.mytradeorganisation.MyTradeOrganisationEdit;
import com.itpearls.agritomarketplace.screen.mytradeorganisation.SelectMyTradeOrganisationBrowse;
import com.itpearls.agritomarketplace.screen.productbyer.ProductByerEdit;
import com.itpearls.agritomarketplace.screen.productbyer.SelectMyProductByerBrowse;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import io.jmix.ui.Notifications;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.ScreenTools;
import io.jmix.ui.component.AppWorkArea;
import io.jmix.ui.component.Button;
import io.jmix.ui.component.Window;
import io.jmix.ui.component.mainwindow.Drawer;
import io.jmix.ui.component.mainwindow.SideMenu;
import io.jmix.ui.icon.JmixIcon;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
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
    @Autowired
    private DataManager dataManager;
    @Autowired
    private Notifications notifications;
    @Autowired
    private CurrentAuthentication currentAuthentication;


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
        if (AgritoGlobalValue.tradeRole != null) {
            if (AgritoGlobalValue.tradeRole.equals(TradeRole.SELLER)) {
                selectSeller();
            } else {
                selectTrader();
            }
        }
    }

    private void selectTrader() {
        AgritoGlobalValue.tradeRole = TradeRole.BUYER;

        Integer tradeOrganisationCount = dataManager.loadValue(
                "select count(e) from MyTradeOrganisation e where e.owner = :owner and e.myTradeOrganisation = true",
                        Integer.class)
                .parameter("owner", (User) currentAuthentication.getUser())
                .one();

        if (tradeOrganisationCount > 1) {
            screenBuilders.lookup(MyTradeOrganisation.class, this)
                    .withOpenMode(OpenMode.DIALOG)
                    .withScreenClass(SelectMyTradeOrganisationBrowse.class)
                    .withSelectHandler(myTradeOrganisation -> {
                        createMenuEditTradeOrganisation(myTradeOrganisation.iterator().next());
                    })
                    .build()
                    .show();
        } else {
            if (tradeOrganisationCount == 1) {
                createMenuEditTradeOrganisation(dataManager
                        .loadValue("select e from MyTradeOrganisation e where e.owner = :owner",
                                MyTradeOrganisation.class)
                        .parameter("owner", (User) currentAuthentication.getUser())
                        .one());
            } else {
                notifications.create(Notifications.NotificationType.WARNING)
                        .withCaption(messageBundle.getMessage("msgWarning"))
                        .withDescription(messageBundle.getMessage("msgNeedCreateMyhousehold"))
                        .show();

                screenBuilders.editor(MyTradeOrganisation.class, this)
                        .withScreenClass(MyTradeOrganisationEdit.class)
                        .newEntity()
                        .withAfterCloseListener(myHouseholdEditAfterScreenCloseEvent ->
                                createMenuEditTradeOrganisation(AgritoGlobalValue.myProductByer))
                        .build()
                        .show();
            }
        }
    }

    private void selectSeller() {
        AgritoGlobalValue.tradeRole = TradeRole.SELLER;
        Integer householdCount = dataManager.loadValue(
                "select count(e) from MyHousehold e where e.owner = :owner and e.myHousehold = true",
                        Integer.class)
                .parameter("owner", (User) currentAuthentication.getUser())
                .one();

        if (householdCount > 1) {
            screenBuilders.lookup(MyHousehold.class, this)
                    .withOpenMode(OpenMode.DIALOG)
                    .withScreenClass(SelectMyHouseholdBrowse.class)
                    .withSelectHandler(myHouseholds -> {
                        createMenuEditHousehold(myHouseholds.iterator().next());
                    })
                    .build()
                    .show();
        } else {
            if (householdCount == 1) {
                createMenuEditHousehold(dataManager
                        .loadValue("select e from MyHousehold e where e.owner = :owner", MyHousehold.class)
                        .parameter("owner", (User) currentAuthentication.getUser())
                        .one());
            } else {
                notifications.create(Notifications.NotificationType.WARNING)
                        .withCaption(messageBundle.getMessage("msgWarning"))
                        .withDescription(messageBundle.getMessage("msgNeedCreateMyhousehold"))
                        .show();

                screenBuilders.editor(MyHousehold.class, this)
                        .withScreenClass(MyHouseholdEdit.class)
                        .newEntity()
                        .withAfterCloseListener(myHouseholdEditAfterScreenCloseEvent ->
                                createMenuEditHousehold(AgritoGlobalValue.myHousehold))
                        .build()
                        .show();
            }
        }
    }

    private void createMenuEditHousehold(MyHousehold household) {

        if (household != null) {
            AgritoGlobalValue.myHousehold = household;
            AgritoGlobalValue.myProductByer = null;
            AgritoGlobalValue.counterparty = AgritoGlobalValue.myHousehold;

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
        } else {
            switchReadOnlySystem();
        }
    }

    private void createMenuEditTradeOrganisation(MyTradeOrganisation myTradeOrganisation) {

        if (myTradeOrganisation != null) {
            AgritoGlobalValue.myProductByer = myTradeOrganisation;
            AgritoGlobalValue.myHousehold = null;
            AgritoGlobalValue.counterparty = AgritoGlobalValue.myProductByer;

            SideMenu.MenuItem myProductByerSideMenuItem = sideMenu.createMenuItem("edit-my-productbyer",
                    AgritoGlobalValue.counterparty.getCounterpartyName(),
                    null,
                    menuItem -> {
                        screenBuilders.editor(MyTradeOrganisation.class, this)
                                .withScreenClass(MyTradeOrganisationEdit.class)
                                .editEntity(AgritoGlobalValue.myProductByer)
                                .build()
                                .show();
                    });
            myProductByerSideMenuItem.setDescription(messageBundle.getMessage("msgEditMyProductBuyer")
                    + " \""
                    + AgritoGlobalValue.myProductByer.getCounterpartyName()
                    + "\"");

            sideMenu.addMenuItem(myProductByerSideMenuItem, 0);
        } else {
            switchReadOnlySystem();
        }
    }

    private void switchReadOnlySystem() {
        notifications.create(Notifications.NotificationType.WARNING)
                .withCaption(messageBundle.getMessage("msgWarning"))
                .withDescription(messageBundle.getMessage("msgSystemReadOnly"))
                .show();

        sideMenu.getMenuItem("Ads").setVisible(false);
        sideMenu.getMenuItem("Bidding").setVisible(false);
        sideMenu.getMenuItem("ProductByer").setVisible(false);
        sideMenu.getMenuItem("ProductByer").setVisible(false);
        sideMenu.getMenuItem("AgriculturalManufacturer").setVisible(false);
    }
}
