package com.skewpixel.rltut2019.ecs.components;

public class HealthComponent implements Component {
    public static final String Name = "HealthComponent";

    public int health;
    public int maxHealth;

    public HealthComponent() {}

    public HealthComponent(int health) {
        this.maxHealth = health;
        this.health = health;
    }

    @Override
    public void reset() {
        health = 0;
        maxHealth = 0;
    }

    @Override
    public String getName() {
        return Name;
    }
}
