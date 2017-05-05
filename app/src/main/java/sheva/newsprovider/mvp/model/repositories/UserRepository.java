package sheva.newsprovider.mvp.model.repositories;

import java.util.List;

import sheva.newsprovider.mvp.model.entities.Interest;
import sheva.newsprovider.mvp.model.entities.User;

public class UserRepository {
    private User mainUser;

    public UserRepository() {
        mainUser = new User();
    }

    public void registerMainUser(String name, String username, String password, String img) {
        if (mainUser == null) {
            mainUser = new User();
        }
        mainUser.setName(name);
        mainUser.setUsername(username);
        mainUser.setPassword(password);
        mainUser.setImg(img);
    }

    public void unregisterMainUser() {
        mainUser = null;
    }

    public String getName() {
        return mainUser.getName();
    }

    public String getUsername() {
        return mainUser.getUsername();
    }

    public String getPassword() {
        return mainUser.getPassword();
    }

    public String getUserImgString() {
        return mainUser.getImgString();
    }

    public List<Interest> getListOfInterests() {
        return mainUser.getInterestList();
    }

    public void setListOfInterests(List<Interest> listOfInterests) {
        mainUser.setInterestList(listOfInterests);
    }

    public void deleteInterests() {
        mainUser.setInterestList(null);
    }
}