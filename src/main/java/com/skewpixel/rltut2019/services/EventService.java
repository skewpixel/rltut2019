package com.skewpixel.rltut2019.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventService {

    Map<String, List<EventListener>> subjectListeners = new HashMap<>();

    public void addEventListener(String subject, EventListener listener) {
        if(!subjectListeners.containsKey(subject)){
            subjectListeners.put(subject, new ArrayList<EventListener>());
        }

        subjectListeners.get(subject).add(listener);
    }

    public void publishEvent(String subject, Object context) {
        if(subjectListeners.containsKey(subject)) {
            for(EventListener listener : subjectListeners.get(subject)) {
                listener.handleEvent(subject, context);
            }
        }
    }
}
