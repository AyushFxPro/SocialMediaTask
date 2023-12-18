package com.example.SocialMedia.Config;

import com.example.SocialMedia.Entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsCreator implements UserDetails {
    String username;
    String password;
    List<GrantedAuthority> authorities;
    public UserDetailsCreator(User user){
        this.username=user.getUsername();
        this.password=user.getPassword();
        String roles[]=user.getRoles().split(",");
        List<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        for(String role:roles){
            SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(role);
            authorities.add(simpleGrantedAuthority);
        }
        this.authorities=grantedAuthorities;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
