package hh.palvelin.bookstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import hh.palvelin.bookstore.model.User;
import hh.palvelin.bookstore.model.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private final UserRepository urepo;

	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.urepo = userRepository;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	User curruser = urepo.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }
}
