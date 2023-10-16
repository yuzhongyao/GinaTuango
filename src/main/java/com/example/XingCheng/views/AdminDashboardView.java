package com.example.XingCheng.views;


import com.example.XingCheng.data.entities.User;
import com.example.XingCheng.services.UserService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "/admin",layout = AdminLayout.class)
public class AdminDashboardView extends VerticalLayout implements BeforeEnterObserver {


    private final UserService userService;

    @Autowired
    public AdminDashboardView(UserService service){
        userService = service;

        H1 userTitle = new H1();
        userTitle.setText("Users");
        Grid<User> usersGrid = new Grid<>(User.class, true);
        configureUserGrid(usersGrid);

        usersGrid.setItems(userService.getUsers());

        add(usersGrid);
    }

    //configures user columns accordingly
    private void configureUserGrid(Grid grid) {

    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
//        Object userObject = VaadinSession.getCurrent().getAttribute("user");
//        if(userObject instanceof User){
//            User user = (User) userObject;
//            //non admins redirected to user dashboard
//            if(!user.isAdmin()){
//                beforeEnterEvent.rerouteTo("/user");
//            }
//            //admin already logged in
//            else{
//                //enter
//            }
//        }
//        else{
//            beforeEnterEvent.rerouteTo(LoginView.class);
//        }
    }
}
