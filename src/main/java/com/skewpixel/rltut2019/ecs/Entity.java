package com.skewpixel.rltut2019.ecs;

import com.skewpixel.rltut2019.ecs.components.Component;

import java.util.HashMap;
import java.util.Map;

public class Entity {

    private Map<String, Component> componentMap = new HashMap<>();

    public void addComponent(Component component) {
        componentMap.put(component.getName(), component);
    }

    public <T extends Component> T getComponentByName(String componentName, Class<T> clazz) {
        if(componentMap.containsKey(componentName)){
            Component c = componentMap.get(componentName);

            if(clazz.isInstance(c)) {
                return clazz.cast(c);
            }
        }

        return null;
    }

    public <T extends Component> T getComponent(Class<T> clazz) {
        for(Map.Entry<String, Component> es : componentMap.entrySet()){
            if(clazz.isInstance(es.getValue())) {
                return clazz.cast(es.getValue());
            }
        }

        return null;
    }
}
