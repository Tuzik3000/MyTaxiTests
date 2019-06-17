package com.mytaxi.storageData;

import dataStructures.UserData;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Component class for Sharing info between Scenarios and Steps
 * UserMap for Sharing userdata Based on Username as a key
 * SharedDataMap is just to store any other info, not related to UseData
 */
@Component
public class UsersMap {
        private HashMap userMap = new HashMap<String, UserData>();
        private HashMap sharedData = new HashMap<String, String>();
        public HashMap getUserMap() {
                return userMap;
        }
        public HashMap getSharedData() {
                return sharedData;
        }
}
