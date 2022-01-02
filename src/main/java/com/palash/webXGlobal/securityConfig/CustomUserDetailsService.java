package com.palash.webXGlobal.securityConfig;

import com.palash.webXGlobal.entities.Users;
import com.palash.webXGlobal.repositories.UsersRepo;
import com.palash.webXGlobal.services.AppServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    //private UsersRepo usersRepo;
    private UsersRepo usersRepo;

    private AppServices appServices;

    @Autowired
    public void CustomerDetailsService(UsersRepo usersRepo, AppServices appServices ){
        this.usersRepo = usersRepo;
        this.appServices = appServices;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        System.out.println("UserDetails username: "+username);
        Users appUser = appServices.findByUsername(username);
        if(appUser.equals(null)){
            System.out.println("appUser is null");
            throw new UsernameNotFoundException("No user present with username : "+username);
        }else{
            System.out.println("return new CustomUserDetails(appUser)");
            return new CustomUserDetails(appUser);
        }
    }

}
