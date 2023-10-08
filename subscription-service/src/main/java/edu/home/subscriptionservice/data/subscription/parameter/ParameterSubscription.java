package edu.home.subscriptionservice.data.subscription.parameter;

import edu.home.subscriptionservice.data.event.Event;
import edu.home.subscriptionservice.data.parameter.InputType;
import edu.home.subscriptionservice.data.parameter.Parameter;
import edu.home.subscriptionservice.data.subscription.Id;
import edu.home.subscriptionservice.data.user.User;
import edu.home.subscriptionservice.dto.UpdateMultiStringParamSubscriptionDTO;
import edu.home.subscriptionservice.dto.UpdateParameterSubscriptionDTO;
import edu.home.subscriptionservice.dto.UpdateSingleStringParamSubscriptionDTO;
import edu.home.subscriptionservice.dto.ParameterSubscriptionDTO;
import jakarta.persistence.*;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static edu.home.subscriptionservice.data.parameter.InputType.*;

@Entity
@Inheritance
//@DiscriminatorValue("PS")
public abstract class ParameterSubscription {

    private static final Map<InputType,
            BiFunction<User, Parameter, ? extends ParameterSubscription>> psFactory = Map.of(
        MULTISELECT, (u, p) -> new MultiStringParameterSubscription(u, p, List.of()),
        INPUT, (u, p) -> new SingleStringParameterSubscription(u, p, ""),
        SELECT, (u, p) -> new SingleStringParameterSubscription(u, p, "")
//        CHECKBOX, (u, p) -> new SingleStringParameterSubscription(u, p, "")
    );

    private static final String USER_ID_COLUMN = "user_id";

    private static final String PARAMETER_ID_COLUMN = "parameter_id";

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = Id.FIRST_ENTITY_ID, column = @Column(name = USER_ID_COLUMN)),
        @AttributeOverride(name = Id.SECOND_ENTITY_ID, column = @Column(name = PARAMETER_ID_COLUMN))
    })
    private Id id = new Id();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = USER_ID_COLUMN, insertable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = PARAMETER_ID_COLUMN, insertable = false, updatable = false)
    private Parameter parameter;

    private boolean checked;

    protected ParameterSubscription() {}

    protected ParameterSubscription(User user, Parameter parameter) {
        this.id = new Id(user.getId(), parameter.getId());
        this.user = user;
        this.parameter = parameter;
    }

    public static ParameterSubscription getParameterSubscription(
        User user, Parameter parameter,
        UpdateParameterSubscriptionDTO addParameterSubscriptionDTO
    ) {
        if (addParameterSubscriptionDTO instanceof
                UpdateMultiStringParamSubscriptionDTO multiStringDTO) {
            return new MultiStringParameterSubscription(
                    user, parameter, multiStringDTO.getValues()
            );
        }
        else if (addParameterSubscriptionDTO instanceof
                UpdateSingleStringParamSubscriptionDTO singleStringDTO) {
            return new SingleStringParameterSubscription(
                    user, parameter, singleStringDTO.getValue()
            );
        }

        throw new IllegalArgumentException();
    }

    public static ParameterSubscription getParameterSubscription(User user, Parameter parameter) {
        return psFactory
                .get(parameter.getInputType())
                .apply(user, parameter);
    }

    public abstract ParameterSubscriptionDTO toDTO();

    public String getParameterName() {
        return parameter.getName();
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public User getUser() {
        return user;
    }

    public String getEventName() {
        return getEvent().getName();
    }

    public String getDomainAppName() {
        return getEvent().getServiceName();
    }

    private Event getEvent() {
        return getParameter().getEvent();
    }
}
