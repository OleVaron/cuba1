package com.company.train.core;

import com.haulmont.addon.bproc.provider.UserProvider;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component(ManagerProvider.NAME)
public class ManagerProvider implements UserProvider {

    public static final String NAME = "train_ManagerProvider";
    private static final String MANAGER_ROLE_NAME = "manager";
    public static final String MANAGER_NOT_FOUND = "managerNotFound";
    public static final String ROLE_NAME = "roleName";

    @Inject
    private DataManager dataManager;
    @Inject
    private Messages messages;

    @Override
    public User get(String executionId) {
        List<User> users = dataManager.load(User.class)
                .query("select u from sec$User u join u.userRoles ur where ur.role.name = :roleName")
                .parameter(ROLE_NAME, MANAGER_ROLE_NAME).list();
        if (users.isEmpty()) {
            throw new RuntimeException(messages.getMessage(this.getClass(), MANAGER_NOT_FOUND));
        }
        return users.get(0);
    }
}