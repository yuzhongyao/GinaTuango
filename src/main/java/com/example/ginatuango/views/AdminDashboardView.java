package com.example.ginatuango.views;


import com.example.ginatuango.services.UserService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "/admin",layout = AdminLayout.class)
public class AdminDashboardView extends VerticalLayout implements BeforeEnterObserver {



    @Autowired
    public AdminDashboardView(UserService service){

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