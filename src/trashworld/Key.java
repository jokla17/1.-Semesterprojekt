/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trashworld;

/**
 *
 * @author dogan
 */
public class Key extends Item{
   public Room unlocks;
   

    /**
     * @return the unlocks
     */
    public Room getUnlocks() {
        return unlocks;
    }

    /**
     * @param unlocks the unlocks to set
     */
    public void setUnlocks(Room unlocks) {
        this.unlocks = unlocks;
    }

    public Key(Room unlocks, String name, String description) {
        super(name, description);
        this.unlocks = unlocks;
    }
    
    
   
    
}
