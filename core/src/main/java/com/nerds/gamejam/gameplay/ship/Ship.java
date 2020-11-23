package com.nerds.gamejam.gameplay.ship;

import com.nerds.gamejam.gameplay.character.CrewMember;
import com.nerds.gamejam.gameplay.ship.inventory.Inventory;

import java.util.List;
import java.util.Set;

public class Ship {

    private final List<CrewMember> crewMembers;
    private final Inventory inventory;

    public Ship(List<CrewMember> crewMembers, Inventory inventory) {
        this.crewMembers = crewMembers;
        this.inventory = inventory;
    }

    public List<CrewMember> getCrewMembers() {
        return crewMembers;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void addCrewMember(CrewMember crewMember) {
        crewMembers.add(crewMember);
    }

    public CrewMember removeCrewMember(CrewMember crewMember) {
        return crewMembers.remove(crewMember) ? crewMember : null;
    }

    public CrewMember getCrewMember(String name) {
        for (CrewMember crewMember: crewMembers) {
            if (crewMember.getName().equalsIgnoreCase(name)) {
                return crewMember;
            }
        }

        return null;
    }
}
