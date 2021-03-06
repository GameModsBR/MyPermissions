package mypermissions.manager;

import mypermissions.Constants;
import mypermissions.api.IPermissionManager;
import mypermissions.api.entities.Group;
import mypermissions.api.entities.User;
import mypermissions.config.json.GroupConfig;
import mypermissions.config.json.UserConfig;

import java.util.UUID;

public class MyPermissionsManager implements IPermissionManager {

    private static final String DEFAULT_GROUP_NAME = "default";

    public final Group.Container groups = new Group.Container();
    public final User.Container users = new User.Container();

    public final GroupConfig groupConfig = new GroupConfig(Constants.CONFIG_FOLDER + "GroupConfig.json", this);
    public final UserConfig userConfig = new UserConfig(Constants.CONFIG_FOLDER + "UserConfig.json", this);

    public MyPermissionsManager() {
    }

    public void loadConfigs() {
        groups.clear();
        users.clear();

        groupConfig.init(groups);
        userConfig.init(users);
    }

    public void saveConfigs() {
        groupConfig.write(groups);
        userConfig.write(users);
    }

    public void saveGroups() {
        groupConfig.write(groups);
    }

    public void saveUsers() {
        userConfig.write(users);
    }

    @Override
    public boolean hasPermission(UUID uuid, String permission) {
        User user = users.get(uuid);

        return user != null && user.hasPermission(permission);
    }
}
