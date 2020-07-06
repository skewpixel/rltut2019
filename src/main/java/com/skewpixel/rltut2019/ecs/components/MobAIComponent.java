package com.skewpixel.rltut2019.ecs.components;

public class MobAIComponent implements Component {
    public static final String Name = "MobAIComponent";

    public String aiName;

    public MobAIComponent() {}

    public MobAIComponent(String aiName) {
        this.aiName = aiName;
    }

    @Override
    public void reset() {
        aiName = null;
    }

    @Override
    public String getName() {
        return Name;
    }
}
