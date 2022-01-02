package com.palash.webXGlobal.securityConfig;

/**
 * @author Majbah Uddin [majbahbuet08@gmail.com]
 * 3:24 PM 19 December,2018
 */

import com.palash.webXGlobal.entities.Users;
import com.palash.webXGlobal.services.AppServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private AppServices appServices;

    @Autowired
    public UserDetailsServiceImpl(AppServices appServices) {
        this.appServices = appServices;
    }



    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users appUser = this.appServices.findByUsername(userName);

        if (appUser == null) {
            System.out.println("User not found! " + userName);

            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }


        System.out.println("Found User: " + appUser.getUsername());

        // [ROLE_USER, ROLE_ADMIN,..]
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        // ROLE_USER, ROLE_ADMIN,..
        GrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");
        grantList.add(authority);

        UserDetails userDetails = new User(appUser.getUsername(),appUser.getPassword(), grantList);
        System.out.println("GG"+userDetails.toString());
        return userDetails;
    }

}

