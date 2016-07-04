//package ua.opinta;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import ua.opinta.core.User;
//import ua.opinta.dao.UserRepository;
//
//import java.util.Collection;
//
///**
// * Created by Administrator on 6/28/2016.
// */
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    @Autowired
//    public CustomUserDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(username).get();
//        if (user == null) {
//            throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
//        }
//        return new UserRepositoryUserDetails(user);
//    }
//
//    private final static class UserRepositoryUserDetails extends User implements UserDetails {
//
//        private static final long serialVersionUID = 1L;
//
//        private UserRepositoryUserDetails(User user) {
//            super(user);
//        }
//
//        @Override
//        public Collection<? extends GrantedAuthority> getAuthorities() {
//            return super.getRoles();
//        }
//
//        @Override
//        public String getUsername() {
//            return super.getUsername();
//        }
//
//        @Override
//        public boolean isAccountNonExpired() {
//            return true;
//        }
//
//        @Override
//        public boolean isAccountNonLocked() {
//            return true;
//        }
//
//        @Override
//        public boolean isCredentialsNonExpired() {
//            return true;
//        }
//
//        @Override
//        public boolean isEnabled() {
//            return true;
//        }
//
//    }
//
//}
