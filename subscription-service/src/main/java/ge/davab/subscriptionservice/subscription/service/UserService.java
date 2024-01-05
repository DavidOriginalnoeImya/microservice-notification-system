package ge.davab.subscriptionservice.subscription.service;

import edu.home.notificationsystem.exception.EntityDoesntExistException;
import ge.davab.subscriptionservice.subscription.data.user.User;
import ge.davab.subscriptionservice.subscription.data.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String userGuid) {
        return userRepository
                .getByGuid(userGuid)
                .orElseThrow(() -> new EntityDoesntExistException("User doesn't exist"));
    }
}
