package ge.davab.subscriptionservice;

import ge.davab.subscriptionservice.subscription.data.user.UserRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ApplicationEventListener {

    private static final Logger logger =
            Logger.getLogger(ApplicationEventListener.class.getName());

    private UserRepository userRepository;

    public ApplicationEventListener(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventListener
    public void handleContextRefreshEvent(ContextRefreshedEvent contextRefreshedEvent) {
    }
}
