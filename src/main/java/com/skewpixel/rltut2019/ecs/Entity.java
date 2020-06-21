package com.skewpixel.rltut2019.ecs;

import com.skewpixel.rltut2019.ecs.components.Component;

import java.util.HashMap;
import java.util.Map;

public class Entity {

    private Map<String, Component> componentMap = new HashMap<>();

    public Entity(){}

    public Entity(Component... components){
        for(Component component :components) {
            addComponent(component);
        }
    }
    public void addComponent(Component component) {
        componentMap.put(component.getName(), component);
    }

    public boolean hasComponent(String componentName) {
        return componentMap.containsKey(componentName);
    }

    public <T extends Component> boolean hasComponent(Class<T> clazz) {
        for(Map.Entry<String, Component> es : componentMap.entrySet()){
            if(clazz.isInstance(es.getValue())) {
                return true;
            }
        }

        return false;
    }

    public <T extends Component> T getComponentByName(String componentName, Class<T> clazz) {
        if(hasComponent(componentName)){
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
