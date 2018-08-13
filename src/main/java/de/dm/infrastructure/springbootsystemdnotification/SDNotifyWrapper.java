package de.dm.infrastructure.springbootsystemdnotification;

import info.faljse.SDNotify.SDNotify;

class SDNotifyWrapper {

    void sendNotify() {
        SDNotify.sendNotify();
    }

    void sendStatus(String status) {
        SDNotify.sendStatus(status);
    }
}
