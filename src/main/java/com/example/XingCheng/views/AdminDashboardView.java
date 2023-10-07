package com.example.XingCheng.views;


import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;

@Route(value = "/admin",layout = AdminLayout.class)
public class AdminDashboardView extends VerticalLayout implements BeforeEnterObserver {


    public AdminDashboardView(){

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
