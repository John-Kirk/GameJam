package com.nerds.gamejam.gameplay.character.stat;

import java.util.Map;

public class StatBlock {

    private Map<Stat, StatValue> stats;

    public StatBlock(Map<Stat, StatValue> stats) {
        this.stats = stats;
    }

    public StatValue getStatValue(Stat stat) {
        return stats.get(stat);
    }
}
