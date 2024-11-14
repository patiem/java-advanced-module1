package repository;

import com.epam.backend.core.dto.Subscription;

import java.util.List;
import java.util.function.Predicate;

public interface SubscriptionRepository {
    void addSubscription(Subscription subscription);
    void addSubscriptions(List<Subscription> subscriptions);
    List<Subscription> getAllSubscriptions();
    List<Subscription> findSubscriptionsByCondition(Predicate<Subscription> condition);
}
