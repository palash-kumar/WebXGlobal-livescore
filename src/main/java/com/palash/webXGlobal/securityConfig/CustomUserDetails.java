package com.palash.webXGlobal.securityConfig;

import com.palash.webXGlobal.entities.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;

@SuppressWarnings("serial")
public class CustomUserDetails extends Users implements UserDetails {

    private Users appUser;
    public CustomUserDetails(Users appUser) {
        super(appUser);
        this.appUser = appUser;

    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled(){

        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.out.println("getAuthorities()");
        return null;
    }

    @Override
    public String getUsername(){
        return super.getUsername();
    }
}
