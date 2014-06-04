package nl.ase.dare2date.confirm;

import nl.ase.dare2date.Subscriber;

/**
 * Created by samuel on 2-6-14.
 */
public class ConfirmRegistration implements IConformRegistration {
    public boolean confirmNewSubscriber(Subscriber subscriber) {
        return true;
    }
}
