/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dsg.aic.ws10.server;

/**
 *
 * @author smolle
 */
public class Addresses {

    private String id;
    private String street;
    private String city;
    private int house = 0;
    private int door = 0;
    private String zipCode;
    private boolean isBilling = false;
    private boolean isOther = false;

    public Addresses() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getDoor() {
        return door;
    }

    public void setDoor(int door) {
        this.door = door;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isIsBilling() {
        return isBilling;
    }

    public void setIsBilling(boolean isBilling) {
        this.isBilling = isBilling;
    }

    public boolean isIsOther() {
        return isOther;
    }

    public void setIsOther(boolean isOther) {
        this.isOther = isOther;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Addresses other = (Addresses) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if ((this.street == null) ? (other.street != null) : !this.street.equals(other.street)) {
            return false;
        }
        if ((this.city == null) ? (other.city != null) : !this.city.equals(other.city)) {
            return false;
        }
        if (this.house != other.house) {
            return false;
        }
        if (this.door != other.door) {
            return false;
        }
        if ((this.zipCode == null) ? (other.zipCode != null) : !this.zipCode.equals(other.zipCode)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 29 * hash + (this.street != null ? this.street.hashCode() : 0);
        hash = 29 * hash + (this.city != null ? this.city.hashCode() : 0);
        hash = 29 * hash + this.house;
        hash = 29 * hash + this.door;
        hash = 29 * hash + (this.zipCode != null ? this.zipCode.hashCode() : 0);
        return hash;
    }
    

}