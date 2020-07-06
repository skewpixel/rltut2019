package com.skewpixel.rltut2019.ecs.components;

import com.skewpixel.rltut2019.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentFactory {
    private static final Logger logger = LoggerFactory.getLogger(Game.class);

    private final Map<String, List<Component> > componentsCache = new HashMap<>();

    public <T extends Component> T getComponentByName(String componentName, Class<T> clazz) {
        Component c;
        if(!componentsCache.containsKey(componentName)) {
            componentsCache.put(componentName, new ArrayList<>());
        }

        List<Component> components = componentsCache.get(componentName);
        if(components.isEmpty()) {
            // create and return a new component
            try {
                c = clazz.newInstance();
            } catch (Exception e) {
                logger.error("Failed to create component with name [{}] and type [{}]: [{}]", componentName, clazz, e);
                return null;
            }
        }
        else {
            c = components.remove(0);
        }

        return clazz.cast(c);
    }

    public void returnComponent(Component component) {
        if(component == null) {
            return;
        }

        component.reset();

        if(!componentsCache.containsKey(component.getName())) {
            componentsCache.put(component.getName(), new ArrayList<>());
        }

        componentsCache.get(component.getName()).add(component);

    }
}
